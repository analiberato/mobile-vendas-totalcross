package com.wmw.treinamento.ui;

import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.dao.ProdutoDAO;
import com.wmw.treinamento.service.PedidoService;
import com.wmw.treinamento.util.Colors;
import com.wmw.treinamento.util.Fonts;
import com.wmw.treinamento.util.Images;
import totalcross.ui.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoViewWindow extends ScrollContainer {
    private Container containerTopo, containerPedido, containerTituloItens, containerItens, containerEdit, containerActions, container;
    private Label page, nome, cpf_cnpj, telefone, email, status, lbl;
    Button btnVoltar, btnAdicionar, btnSalvar, btnFechar, btnLixeira;
    private Pedido pedido;
    private PedidoService pedidoService = new PedidoService();
    private List<ItemPedido> itens = new ArrayList<>();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    public PedidoViewWindow(Pedido pedido){
        this.pedido = pedido;
    }

    public void initUI() {

        pedido = pedidoService.inicializarPedido(pedido);
        itens = pedidoService.retornaListaItens(pedido);

        containerTopo = new Container();
        add(containerTopo, LEFT, TOP, FILL, PARENTSIZE + 8);

        btnVoltar = new Button("<");
        btnVoltar.setBorder(Container.BORDER_NONE);
        btnVoltar.setFont(Fonts.sansIcons);
        containerTopo.add(btnVoltar, LEFT, CENTER, PREFERRED, PREFERRED);
        btnVoltar.addPressListener((e) -> {
            MainWindow.getMainWindow().swap(new ClienteListWindow());
        });

        page = new Label("Pedido", CENTER, Colors.BLACK, true);
        page.setFont(Fonts.sansRegularBiggerSizeBold);
        containerTopo.add(page, CENTER, CENTER, PREFERRED, PREFERRED);

        containerPedido = new Container();
        containerPedido.borderColor = Colors.GRAY;
        containerPedido.setBorderStyle(Container.BORDER_TOP);

        containerPedido.setInsets(25, 25, 25, 25);
        add(containerPedido, LEFT, AFTER, FILL, PARENTSIZE + 25);

        nome = new Label("Pedido " + pedido.getId());
        nome.setFont(Fonts.sansRegularBiggerSizeBold);
        containerPedido.add(nome, SAME, AFTER, PREFERRED, PREFERRED);

        cpf_cnpj = new Label("Data Emissão: " + pedido.getDataEmissao().getSQLString());
        cpf_cnpj.setFont(Fonts.sansRegularDefaultSize);
        containerPedido.add(cpf_cnpj, SAME , AFTER, PREFERRED, PREFERRED);

        telefone = new Label("Data Entrega: " + pedido.getDataEntrega().getSQLString() + "");
        telefone.setFont(Fonts.sansRegularDefaultSize);
        containerPedido.add(telefone, SAME , AFTER, PREFERRED, PREFERRED);

        email = new Label("Valor Total: " + pedido.getTotalPedido() + "");
        email.setFont(Fonts.sansRegularDefaultSize);
        containerPedido.add(email, SAME , AFTER, PREFERRED, PREFERRED);

        status = new Label("Status: " + pedido.getStatus());
        status.setFont(Fonts.sansRegularDefaultSize);
        containerPedido.add(status, SAME , AFTER, PREFERRED, PREFERRED);

        containerTituloItens = new Container();
        containerTituloItens.setInsets(25, 25, 5, 5);
        add(containerTituloItens, LEFT, AFTER, FILL, PARENTSIZE + 5);

        page = new Label("Items");
        page.setFont(Fonts.sansRegularBiggerSizeBold);
        containerTituloItens.add(page, LEFT, TOP, PREFERRED, PREFERRED);

        btnAdicionar = new Button("+");
        btnAdicionar.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        btnAdicionar.setBorder(Container.BORDER_ROUNDED);
        btnAdicionar.setFont(Fonts.sansIcons);
        containerTituloItens.add(btnAdicionar, RIGHT, SAME, 25 , 25);
        btnAdicionar.addPressListener((e) -> {
            MainWindow.getMainWindow().swap(new ItemPedidoCadWindow(pedido));
        });

        for (ItemPedido itemPedido: itens) {

            container = new Container();
            container.borderColor = Colors.GRAY;
            container.setBorderStyle(Container.BORDER_TOP);
            add(container, LEFT, AFTER, FILL, 1);

            containerItens = new Container();
            containerItens.setInsets(35, 35, 25, 25);
            add(containerItens, LEFT, AFTER, PARENTSIZE + 75, PARENTSIZE + 22);

            try {
                lbl = new Label(produtoDAO.nomeProduto(itemPedido.getId_produto()) + " x" + itemPedido.getQuantidade());
                lbl.setFont(Fonts.sansRegularBiggerSizeBold);
                containerItens.add(lbl, LEFT, SAME, PREFERRED, PREFERRED);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            lbl = new Label("Preço unitário: R$ " + itemPedido.getPrecoUnitario() + "");
            lbl.setFont(Fonts.sansRegularDefaultSize);
            containerItens.add(lbl, LEFT, AFTER, PREFERRED, PREFERRED);

            lbl = new Label("Desconto: " + itemPedido.getDesconto() + "");
            lbl.setFont(Fonts.sansRegularDefaultSize);
            containerItens.add(lbl, LEFT, AFTER, PREFERRED, PREFERRED);

            lbl = new Label("Valor Total: R$ " + itemPedido.getTotalItem() + "");
            lbl.setFont(Fonts.sansRegularDefaultSize);
            containerItens.add(lbl, LEFT, AFTER, PREFERRED, PREFERRED);

            containerEdit = new Container();
            containerEdit.setInsets(0, 25, 40, 0);
            add(containerEdit, RIGHT, SAME, PARENTSIZE + 20, PARENTSIZE + 22);

            btnLixeira = new Button(Images.icon_lixeira);
            btnLixeira.setBorder(Container.BORDER_NONE);
            btnLixeira.setFont(Fonts.sansRegularBiggerSizeBold);
            containerEdit.add(btnLixeira, RIGHT, SAME, PREFERRED, PREFERRED);
            btnLixeira.addPressListener((e) -> {
                MainWindow.getMainWindow().swap(new PedidoViewWindow(pedidoService.deletarItem(itemPedido.getId(), pedido, itemPedido)));
            });

        }

        if (pedido.getStatus() != "FECHADO"){

            containerActions= new Container();
            containerActions.setInsets(25, 25, 25, 25);
            add(containerActions, LEFT, AFTER + 5, FILL, PARENTSIZE + 15);

            btnSalvar = new Button("SALVER PEDIDO");
            btnSalvar.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
            btnSalvar.borderColor = Colors.PURPLE;
            btnSalvar.setBorder(Container.BORDER_ROUNDED);

            btnSalvar.setFont(Fonts.sansRegularBiggerSizeBold);
            containerActions.add(btnSalvar, LEFT, CENTER, PARENTSIZE + 48 , PARENTSIZE + 95);
            btnSalvar.addPressListener((e) -> {
                try {
                    pedidoService.atualizarPedido(pedido);
                    MainWindow.getMainWindow().swap(new ClienteViewWindow(pedido.getId_cliente()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            });

            btnFechar = new Button("FECHAR PEDIDO");
            btnFechar.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
            btnFechar.setBorder(Container.BORDER_ROUNDED);
            btnFechar.setFont(Fonts.sansRegularBiggerSizeBold);
            containerActions.add(btnFechar, RIGHT, CENTER, PARENTSIZE + 48 , PARENTSIZE + 95);
            btnFechar.addPressListener((e) -> {
                try {
                    if (pedidoService.verificaSeTemMinimoUmItem(pedido)) {
                        MainWindow.getMainWindow().swap(new ClienteViewWindow(pedido.getId_cliente()));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }



    }

}
