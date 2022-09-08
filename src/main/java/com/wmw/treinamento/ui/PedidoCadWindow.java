package com.wmw.treinamento.ui;

import com.wmw.treinamento.dao.PedidoDAO;
import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.service.PedidoService;
import com.wmw.treinamento.util.Colors;
import com.wmw.treinamento.util.Fonts;
import totalcross.ui.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoCadWindow extends ScrollContainer {
    private Container containerTopo, containerCadastro, containerActions;
    Button btnFechar, btnAdicionar, btnSalvar;
    private Label page, lblValorTotal, valorTotal, lblDataEntrega;
    private Edit dataentrega;
    private Pedido pedido;
    private List<ItemPedido> itens = new ArrayList<>();
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private PedidoService pedidoService = new PedidoService();
    public PedidoCadWindow(Pedido pedido) {
        this.pedido = pedido;
    }

    public void initUI() {

        containerTopo = new Container();
        add(containerTopo, LEFT, TOP, FILL, PARENTSIZE + 8);

        btnFechar = new Button("x");
        btnFechar.setBorder(Container.BORDER_NONE);
        btnFechar.setFont(Fonts.sansIcons);
        containerTopo.add(btnFechar, LEFT, CENTER, PREFERRED, PREFERRED);
        btnFechar.addPressListener((e) -> {
            try {
                MainWindow.getMainWindow().swap(new ClienteViewWindow(pedido.getId_cliente()));
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

        valorTotal = new Label(pedidoService.retornaListaProdutos(pedido));
        valorTotal.setFont(Fonts.sansRegularDefaultSize);
        containerCadastro.add(valorTotal, LEFT, AFTER, PREFERRED, PREFERRED);

        lblDataEntrega = new Label("Data Entrega: " );
        lblDataEntrega.setFont(Fonts.sansRegularDefaultSizeBold);
        containerCadastro.add(lblDataEntrega, LEFT, AFTER, PREFERRED, PREFERRED);

        dataentrega = new Edit();
        dataentrega.caption = "YYYY-MM-DD";
        if (pedido.getDataEntrega() != null)
            dataentrega.setText(pedido.getDataEntrega().getSQLString());
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
            pedidoService.adicionarItens(pedido, itens);
            MainWindow.getMainWindow().swap(new ItemPedidoCadWindow(pedido));
        });

        btnSalvar = new Button("SALVAR PEDIDO");
        btnSalvar.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        btnSalvar.setBorder(Container.BORDER_ROUNDED);
        btnSalvar.setFont(Fonts.sansRegularBiggerSizeBold);
        containerActions.add(btnSalvar, RIGHT, CENTER, PARENTSIZE + 47 , PARENTSIZE + 95);
        btnSalvar.addPressListener((e) -> {
            if (pedidoService.verificaDataEntrega(dataentrega.getText(), pedido)) {
                MainWindow.getMainWindow().swap(new PedidoViewWindow(pedido));
            }
        });


    }
}