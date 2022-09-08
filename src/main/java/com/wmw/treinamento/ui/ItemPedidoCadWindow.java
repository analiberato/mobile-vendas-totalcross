package com.wmw.treinamento.ui;


import com.wmw.treinamento.domain.Cliente;
import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.domain.Produto;
import com.wmw.treinamento.dao.ProdutoDAO;
import com.wmw.treinamento.service.ItemPedidoService;
import com.wmw.treinamento.util.Colors;
import com.wmw.treinamento.util.Fonts;
import totalcross.sys.InvalidNumberException;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;
import totalcross.util.BigDecimal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoCadWindow extends ScrollContainer {


    private Container containerTopo, containerCadastroInicial, containerCadastroFinal, containerActions;
    Button btnFechar, btnAdicionar, btnSalvar;
    private Label precoTotalItem, page, lblValorTotal, valorTotal, lblProduto, lblPrecoUnitario, precoUnitario, lblQuantidade, lblDesconto, lblValorTotalItem;
    private Edit quantidade, desconto;
    private Pedido pedido = new Pedido();
    private ComboBox simpleComboBox;
    private Produto produto;
    private ItemPedido item;
    private ItemPedidoService service = new ItemPedidoService();

    private List<Produto> produtos = new ArrayList<>();
    private ItemPedidoService itemPedidoService = new ItemPedidoService();

    public ItemPedidoCadWindow(Pedido pedido) {
        this.pedido = pedido;
    }

    public ItemPedidoCadWindow(Cliente cliente) {
        pedido.setId_cliente(cliente.getId());
    }

    public void initUI() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        containerTopo = new Container();
        add(containerTopo, LEFT, TOP, FILL, PARENTSIZE + 8);

        btnFechar = new Button("x");
        btnFechar.setBorder(Container.BORDER_NONE);
        btnFechar.setFont(Fonts.sansIcons);
        containerTopo.add(btnFechar, LEFT, CENTER, PREFERRED, PREFERRED);
        btnFechar.addPressListener((e) -> {

            MessageBox messageBox = new MessageBox("Messagem", "Você irá perder o seu pedido. Deseja continuar mesmo assim?", new String[] { "Sim", "Não" });
            messageBox.popup();

            if(messageBox.getPressedButtonIndex() == 0) {
                try {
                    MainWindow.getMainWindow().swap(new ClienteViewWindow(pedido.getId_cliente()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        page = new Label("Pedido", CENTER, Colors.BLACK, true);
        page.setFont(Fonts.sansRegularBiggerSizeBold);
        containerTopo.add(page, CENTER, CENTER, PREFERRED, PREFERRED);

        containerCadastroInicial = new Container();
        containerCadastroInicial.borderColor = Colors.GRAY;
        containerCadastroInicial.setBorderStyle(Container.BORDER_TOP);
        containerCadastroInicial.setInsets(25, 25, 25, 25);
        add(containerCadastroInicial, LEFT, AFTER, FILL, PARENTSIZE + 30);

        lblValorTotal = new Label("Valor Total do Pedido: " );
        lblValorTotal.setFont(Fonts.sansRegularDefaultSizeBold);
        containerCadastroInicial.add(lblValorTotal, LEFT, AFTER, PREFERRED, PREFERRED);

        valorTotal = new Label("R$ " + pedido.getTotalPedido()+"");
        valorTotal.setFont(Fonts.sansRegularDefaultSize);
        containerCadastroInicial.add(valorTotal, AFTER, SAME, PREFERRED, PREFERRED);

        lblProduto = new Label("Produto");
        lblProduto.setFont(Fonts.sansRegularDefaultSizeBold);
        lblProduto.setInsets(0, 0, 25, 5);
        containerCadastroInicial.add(lblProduto, LEFT, AFTER, PREFERRED, PREFERRED);

        ComboBox.usePopupMenu = false;
        simpleComboBox = new ComboBox(service.retornaListaProdutos());
        simpleComboBox.setFont(Fonts.sansRegularDefaultSize);
        simpleComboBox.caption = "Produtos";
        simpleComboBox.setBorderStyle(Container.BORDER_LOWERED);
        simpleComboBox.setInsets(5,5,5,5);
        containerCadastroInicial.add(simpleComboBox, LEFT, AFTER, FILL, PREFERRED);

        lblPrecoUnitario = new Label("Preço Unitário");
        lblPrecoUnitario.setFont(Fonts.sansRegularDefaultSizeBold);
        lblProduto.setInsets(0, 0, 25, 0);
        containerCadastroInicial.add(lblPrecoUnitario, SAME, AFTER, PREFERRED, PREFERRED);

        precoUnitario = new Label("R$ 0.0");
        precoUnitario.setFont(Fonts.sansRegularDefaultSize);
        precoUnitario.setInsets(5,0,25,0);
        containerCadastroInicial.add(precoUnitario, SAME, AFTER, PARENTSIZE, PREFERRED);

        simpleComboBox.addPressListener((e) -> {
            Object obj = simpleComboBox.getSelectedItem();
            String str = obj.toString();
            try {
                produto = produtoDAO.detalharProdutoByNome(str);
                item = new ItemPedido();
                item.setId_produto(produto.getId());
                item.setPrecoUnitario(produto.getPreco());

                precoUnitario.setText("R$ " + produto.getPreco() + "");
                quantidade.caption = "0";
                quantidade.setText(""+item.getQuantidade());
                desconto.caption = "0.0";
                desconto.setText(""+item.getDesconto());
                precoTotalItem.setText("R$ " + item.getTotalItem()+ "");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });


        containerCadastroFinal = new Container();
        containerCadastroFinal.setInsets(25, 25, 0, 0);
        add(containerCadastroFinal, LEFT, AFTER, FILL, FILL);

        lblQuantidade = new Label("Quantidade");
        lblQuantidade.setFont(Fonts.sansRegularDefaultSizeBold);
        lblQuantidade.setInsets(0, 0, 0, 25);
        containerCadastroFinal.add(lblQuantidade, SAME, AFTER, PREFERRED, PREFERRED);

        quantidade = new Edit();
        quantidade.caption = "0";
        quantidade.setMode(Edit.CURRENCY);
        quantidade.setKeyboard(Edit.KBD_NUMERIC);
        quantidade.setFont(Fonts.sansRegularDefaultSizeBold);
        containerCadastroFinal.add(quantidade, SAME, AFTER, PREFERRED, PREFERRED);

        lblDesconto = new Label("Desconto");
        lblDesconto.setFont(Fonts.sansRegularDefaultSizeBold);
        lblDesconto.setInsets(0, 0, 25, 3);
        containerCadastroFinal.add(lblDesconto, SAME, AFTER, PREFERRED, PREFERRED);

        desconto = new Edit();
        desconto.caption = "0.0";
        desconto.setMode(Edit.CURRENCY);
        desconto.setKeyboard(Edit.KBD_CALCULATOR);
        desconto.setFont(Fonts.sansRegularDefaultSizeBold);
        containerCadastroFinal.add(desconto, SAME, AFTER, PREFERRED, PREFERRED);

        lblValorTotalItem = new Label("Valor Total");
        lblValorTotalItem.setFont(Fonts.sansRegularDefaultSizeBold);
        lblValorTotalItem.setInsets(0, 0, 25, 3);
        containerCadastroFinal.add(lblValorTotalItem, SAME, AFTER, PREFERRED, PREFERRED);

        precoTotalItem = new Label("0.0");
        precoTotalItem.setFont(Fonts.sansRegularDefaultSize);
        precoTotalItem.setInsets(5,0,0,0);
        containerCadastroFinal.add(precoTotalItem, SAME, AFTER, FILL, PREFERRED);

        quantidade.addPressListener((e) -> {
            try {
                item = service.calculaQuantidade(item, BigDecimal.valueOf(Double.parseDouble(quantidade.getText())));
                precoTotalItem.setText("R$ " + item.getTotalItem() + "");
            } catch (InvalidNumberException ex) {
                throw new RuntimeException(ex);
            }

        });

        desconto.addPressListener((e) -> {
            try {
                item = service.calculaDesconto(desconto.getText(), produto.getPreco(), item);
                precoUnitario.setText("R$ " + item.getPrecoUnitario() + "");
                precoTotalItem.setText("R$ " + item.getTotalItem()+ "");
            } catch (InvalidNumberException ex) {
                throw new RuntimeException(ex);
            }

        });


        containerActions = new Container();
        containerActions.setInsets(15, 15, 25, 25);
        add(containerActions, LEFT, BOTTOM, FILL, PARENTSIZE + 15);

        btnAdicionar = new Button("ADICIONAR + ITEMS");
        btnAdicionar.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        btnAdicionar.borderColor = Colors.PURPLE;
        btnAdicionar.setBorder(Container.BORDER_ROUNDED);
        btnAdicionar.setFont(Fonts.sansRegularBiggerSizeBold);
        containerActions.add(btnAdicionar, LEFT, CENTER, PARENTSIZE + 52 , PARENTSIZE + 95);
        btnAdicionar.addPressListener((e) -> {

            if (itemPedidoService.verificaItem(item)){
                if(itemPedidoService.verificaQuantidadePedido(item)) {
                    itemPedidoService.adicionaItem(pedido, item);
                    MainWindow.getMainWindow().swap(new ItemPedidoCadWindow(pedido));
                }
            }
        });

        btnSalvar = new Button("SALVAR PEDIDO");
        btnSalvar.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        btnSalvar.setBorder(Container.BORDER_ROUNDED);
        btnSalvar.setFont(Fonts.sansRegularBiggerSizeBold);
        containerActions.add(btnSalvar, RIGHT, CENTER, PARENTSIZE + 47 , PARENTSIZE + 95);
        btnSalvar.addPressListener((e) -> {

            if (itemPedidoService.verificaItem(item)) {
                if(itemPedidoService.verificaQuantidadePedido(item)) {
                    itemPedidoService.adicionaItem(pedido, item);
                    MainWindow.getMainWindow().swap(new PedidoCadWindow(pedido));
                }
            }
        });


    }

}
