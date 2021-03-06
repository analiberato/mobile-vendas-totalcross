package com.wmw.treinamento.ui;

import com.wmw.treinamento.dao.ItemPedidoDAO;
import com.wmw.treinamento.dao.PedidoDAO;
import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.domain.Produto;
import com.wmw.treinamento.dao.ProdutoDAO;
import com.wmw.treinamento.service.PedidoService;
import com.wmw.treinamento.util.Colors;
import com.wmw.treinamento.util.Fonts;
import totalcross.sys.Settings;
import totalcross.ui.*;
import totalcross.util.Date;
import totalcross.util.InvalidDateException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PedidoCadWindow extends ScrollContainer {

    private Container containerTopo, containerCadastro, containerActions;
    Button btnFechar, btnAdicionar, btnSalvar;
    private Label page, lblValorTotal, valorTotal, lblDataEntrega;
    private Edit dataentrega;
    private Pedido pedido;
    private List<ItemPedido> itens = new ArrayList<>();
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private PedidoService service = new PedidoService();
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    public PedidoCadWindow(Pedido pedido) {
        this.pedido = pedido;
    }

    public void initUI() {

//        try {
//            produtos = produtoDAO.listarProduto();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        //AQUI LISTAR RETORNAPEDIDOS STRING
//        String items = "";
//        for (ItemPedido item: pedido.getItens()) {
//            for (Produto produto: produtos) {
//                if (produto.getId() == item.getId_produto())
//                    items += produto.getNome();
//            }
//            items += " x" + item.getQuantidade() + "\n";
//        }


        containerTopo = new Container();
        add(containerTopo, LEFT, TOP, FILL, PARENTSIZE + 8);

        btnFechar = new Button("x");
        btnFechar.setBorder(Container.BORDER_NONE);
        btnFechar.setFont(Fonts.sansIcons);
        containerTopo.add(btnFechar, LEFT, CENTER, PREFERRED, PREFERRED);
        btnFechar.addPressListener((e) -> {

            ClienteViewWindow menu = null;
            try {
                menu = new ClienteViewWindow(pedido.getId_cliente());
                MainWindow.getMainWindow().swap(menu);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        });

        page = new Label("Pedido", CENTER, Colors.BLACK, true);
        page.setFont(Fonts.sansRegularBiggerSizeBold);
        containerTopo.add(page, CENTER, CENTER, PREFERRED, PREFERRED);

        containerCadastro = new Container();
        containerCadastro.borderColor = Colors.GRAY;
        containerCadastro.setBorderStyle(Container.BORDER_TOP);
        containerCadastro.setInsets(25, 25, 25, 25);
        add(containerCadastro, LEFT, AFTER, FILL, FILL);


        lblValorTotal = new Label("Valor Total do Pedido: " );
        lblValorTotal.setFont(Fonts.sansRegularDefaultSizeBold);
        containerCadastro.add(lblValorTotal, LEFT, AFTER, PREFERRED, PREFERRED);

        valorTotal = new Label("R$ " + pedido.getTotalPedido()+"");
        valorTotal.setFont(Fonts.sansRegularDefaultSize);
        containerCadastro.add(valorTotal, AFTER, SAME, PREFERRED, PREFERRED);

        valorTotal = new Label(service.retornaListaItens(pedido));
        valorTotal.setFont(Fonts.sansRegularDefaultSize);
        containerCadastro.add(valorTotal, LEFT, AFTER, PREFERRED, PREFERRED);

        lblDataEntrega = new Label("Data Entrega: " );
        lblDataEntrega.setFont(Fonts.sansRegularDefaultSizeBold);
        containerCadastro.add(lblDataEntrega, LEFT, AFTER, PREFERRED, PREFERRED);

        //VALIDAR SE ?? UMA DATA FUTURA
        dataentrega = new Edit();
        if (pedido.getDataEntrega() != null)
            dataentrega.caption = ""+ pedido.getDataEntrega().getSQLString();
        else
            dataentrega.caption = "YYYY-MM-DD";
        dataentrega.setMode(Edit.CURRENCY);
        dataentrega.setKeyboard(Edit.KBD_CALENDAR);
        dataentrega.setFont(Fonts.sansRegularDefaultSizeBold);
        containerCadastro.add(dataentrega, SAME, AFTER, PREFERRED, PREFERRED);

        containerActions = new Container();
        containerActions.setInsets(25, 25, 25, 25);
        add(containerActions, LEFT, BOTTOM, FILL, PARENTSIZE + 15);

        btnAdicionar = new Button("ADICIONAR + ITEMS");
        btnAdicionar.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        btnAdicionar.borderColor = Colors.PURPLE;
        btnAdicionar.setBorder(Container.BORDER_ROUNDED);

        btnAdicionar.setFont(Fonts.sansRegularBiggerSizeBold);
        containerActions.add(btnAdicionar, LEFT, CENTER, PARENTSIZE + 52 , PARENTSIZE + 95);
        btnAdicionar.addPressListener((e) -> {

            try {
                if (pedido.getItens().isEmpty()) {
                    itens = itemPedidoDAO.listarItemById(pedido.getId());
                    pedido = pedidoDAO.detalharPedido(pedido.getId());

                    for (ItemPedido item: itens) {
                        pedido.getItens().add(item);
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            ItemPedidoCadWindow menu = new ItemPedidoCadWindow(pedido);
            MainWindow.getMainWindow().swap(menu);

        });

        btnSalvar = new Button("SALVAR PEDIDO");
        btnSalvar.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        btnSalvar.setBorder(Container.BORDER_ROUNDED);
        btnSalvar.setFont(Fonts.sansRegularBiggerSizeBold);
        containerActions.add(btnSalvar, RIGHT, CENTER, PARENTSIZE + 47 , PARENTSIZE + 95);
        btnSalvar.addPressListener((e) -> {
            Date date_emissao = new Date();
            Date date_entrega = new Date();

            try {
                if (pedido.getDataEmissao() == null)
                    date_emissao.set(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()), Settings.DATE_YMD);
                if (pedido.getDataEntrega() == null)
                    date_entrega.set(dataentrega.getText(), Settings.DATE_YMD);
            } catch (InvalidDateException ex) {
                throw new RuntimeException(ex);
            }

            //VERIFICAR TODAS AS INFORMA????E EST??O DENTRO DE PEDIDO
            try {
                if (pedidoDAO.retornaExisteId(pedido.getId()) == -1) {
                    pedido.setDataEmissao(date_emissao);
                    pedido.setDataEntrega(date_entrega);
                    pedidoDAO.inserirPedido(pedido);
                    pedido.setId(pedidoDAO.retornaUltimoId());
                    for (ItemPedido item : pedido.getItens()) {
                        item.setId_pedido(pedido.getId());
                        itemPedidoDAO.inserirItem(item);
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            PedidoViewWindow menu = new PedidoViewWindow(pedido);
            MainWindow.getMainWindow().swap(menu);

        });


    }
}
