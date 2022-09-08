package com.wmw.treinamento.ui;

import com.wmw.treinamento.domain.Cliente;
import com.wmw.treinamento.service.ClienteService;
import com.wmw.treinamento.util.Colors;
import com.wmw.treinamento.util.Fonts;
import totalcross.ui.*;

import java.util.ArrayList;
import java.util.List;

public class ClienteListWindow extends ScrollContainer {

    private Container containerTopo, container;
    private Button btn1, btnCliente;
    private Label vendas, lbl;
    private List<Cliente> clientes = new ArrayList<>();

    private Cliente cliente;

    public ClienteListWindow() {
    }

    public void initUI() {
        containerTopo = new Container();
        add(containerTopo, LEFT, TOP, FILL, PARENTSIZE + 8);

        btn1 = new Button("<");
        btn1.setBorder(Container.BORDER_NONE);
        btn1.setFont(Fonts.sansIcons);
        containerTopo.add(btn1, LEFT, CENTER, PREFERRED, PREFERRED);
        btn1.addPressListener((e) -> {
            MainWindow.getMainWindow().swap(new ClienteListWindow());
        });

        vendas = new Label("Clientes", CENTER, Colors.BLACK, true);
        vendas.setFont(Fonts.sansRegularBiggerSizeBold);
        containerTopo.add(vendas, CENTER, CENTER, PREFERRED, PREFERRED);

        clientes = new ClienteService().listarClientes();

        for (Cliente cliente: clientes) {
            container = new Container();
            container.borderColor = Colors.GRAY;
            container.setBorderStyle(Container.BORDER_TOP);
            container.setInsets(35, 35, 25, 25);
            add(container, LEFT, AFTER, FILL, PARENTSIZE + 13);

            btnCliente = new Button(cliente.getNome());
            btnCliente.setBorder(Container.BORDER_NONE);
            btnCliente.paddingLeft = 0;
            btnCliente.paddingBottom = 2;
            btnCliente.setFont(Fonts.sansRegularBiggerSizeBold);
            container.add(btnCliente, LEFT, SAME, PREFERRED, PREFERRED);

            lbl = new Label(cliente.getCpf_cnpj());
            lbl.setFont(Fonts.sansRegularDefaultSize);
            container.add(lbl, LEFT, AFTER, PREFERRED, PREFERRED);

            btnCliente.addPressListener((e) -> {
                MainWindow.getMainWindow().swap(new ClienteViewWindow(cliente));
            });

        }

        container = new Container();
        container.borderColor = Colors.GRAY;
        container.setBorderStyle(Container.BORDER_TOP);
        container.setInsets(35, 35, 25, 25);
        add(container, LEFT, AFTER, FILL, PARENTSIZE + 13);


    }
}