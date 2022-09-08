package com.wmw.treinamento.ui;

import com.wmw.treinamento.service.APIService;
import com.wmw.treinamento.util.Colors;
import com.wmw.treinamento.util.Images;
import com.wmw.treinamento.util.MaterialConstants;
import totalcross.ui.*;
import totalcross.ui.dialog.ProgressBox;

public class MenuWindow extends Container {
    private Container back;
    private ImageControl logo, logon;
    private Label vendas;

    private ProgressBox pb;

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
            APIService API = new APIService();
            API.execute();
        });
    }

}

