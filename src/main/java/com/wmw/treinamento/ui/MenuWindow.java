package com.wmw.treinamento.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wmw.treinamento.api.ClienteAPI;
import com.wmw.treinamento.api.PedidoAPI;
import com.wmw.treinamento.api.ProdutoAPI;
import com.wmw.treinamento.dao.ItemPedidoDAO;
import com.wmw.treinamento.dao.PedidoDAO;
import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.dto.PedidoDTO;
import com.wmw.treinamento.util.Colors;
import com.wmw.treinamento.util.DatabaseConnection;
import com.wmw.treinamento.util.Images;
import com.wmw.treinamento.util.MaterialConstants;
import totalcross.sql.Connection;
import totalcross.sql.Statement;
import totalcross.ui.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuWindow extends Container {
    private Container back;
    private ImageControl logo, logon;
    private Label vendas;

    public MenuWindow() {
    }

    public void initUI() {
        Images.loadImages(fmH);
        back = new Container();
        add(back, LEFT, TOP, FILL, FILL);
        setBackForeColors(Colors.WHITE, Colors.WHITE);

        logo = new ImageControl(Images.logo_azul);
        logo.scaleToFit = true;
        logo.transparentBackground = true;
        add(logo, CENTER - (3 * MaterialConstants.COMPONENT_SPACING), TOP + MaterialConstants.BORDER_SPACING,
                PARENTSIZE + 30, PARENTSIZE + 15);

        vendas = new Label("Vendas");
        vendas.transparentBackground = true;
        vendas.setForeColor(Colors.BLUE_BUTTONS);
        add(vendas, AFTER + MaterialConstants.COMPONENT_SPACING, CENTER_OF, logo);

        Container cont = new Container();
        cont.transparentBackground = true;
        back.add(cont, LEFT + MaterialConstants.BORDER_SPACING, TOP + 90, FILL - MaterialConstants.BORDER_SPACING,
                PARENTSIZE + 50);

        Button btnprodutos = new Button("Produtos");
        btnprodutos.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        cont.add(btnprodutos, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
        btnprodutos.addPressListener((e) -> {
            MainWindow.getMainWindow().swap(new ProdutoListWindow());
        });

        Button btnclientes = new Button("Clientes");
        btnclientes.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        cont.add(btnclientes, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
        btnclientes.addPressListener((e) -> {
            MainWindow.getMainWindow().swap(new ClienteListWindow());
        });

        Button btnsincronizar = new Button("Sincronizar dados");
        btnsincronizar.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        cont.add(btnsincronizar, LEFT, AFTER + MaterialConstants.COMPONENT_SPACING, FILL, PREFERRED);
        btnsincronizar.addPressListener((e) -> {

            try {
                DatabaseConnection connection = new DatabaseConnection();
                Connection dbcon = connection.getConnection();

                dbcon.createStatement().execute("DELETE FROM cliente");
                dbcon.createStatement().execute("DELETE FROM produto");

                ClienteAPI cliente = new ClienteAPI();
                cliente.getPressListener();

                ProdutoAPI produto = new ProdutoAPI();
                produto.getPressListener();

                PedidoAPI pedidoAPI = new PedidoAPI();
                pedidoAPI.getPressListener();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
    }

}

