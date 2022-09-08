package com.wmw.treinamento.ui;

import com.wmw.treinamento.domain.Cliente;
import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.dao.ClienteDAO;
import com.wmw.treinamento.service.ClienteService;
import com.wmw.treinamento.util.Colors;
import com.wmw.treinamento.util.Fonts;
import com.wmw.treinamento.util.Images;
import totalcross.ui.*;
import totalcross.ui.dialog.MessageBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteViewWindow extends ScrollContainer {

    private Container containerTopo, containerCliente, containerTituloItens, containerActions, container;
    Button btn1, btn2, btnLixeira, btnLapis;
    private Label page, nome, cpf_cnpj, telefone, email, lbl;
    private Cliente cliente;
    private List<Pedido> pedidos = new ArrayList<>();
    private ClienteDAO clienteDAO = new ClienteDAO();

    private ClienteService clienteService = new ClienteService();

    public ClienteViewWindow(int id) throws SQLException {
        this.cliente = clienteDAO.detalharCliente(id);
    }

    public ClienteViewWindow(Cliente cliente) {
        this.cliente = cliente;
    }

    public void initUI() {

        clienteService.inicializarCliente(cliente);
        pedidos = clienteService.inicializarPedidos(cliente);

        containerTopo = new Container();
        add(containerTopo, LEFT, TOP, FILL, PARENTSIZE + 8);

        btn1 = new Button("<");
        btn1.setBorder(Container.BORDER_NONE);
        btn1.setFont(Fonts.sansIcons);
        containerTopo.add(btn1, LEFT, CENTER, PREFERRED, PREFERRED);
        btn1.addPressListener((e) -> {
            MainWindow.getMainWindow().swap(new ClienteListWindow());
        });

        page = new Label("Cliente", CENTER, Colors.BLACK, true);
        page.setFont(Fonts.sansRegularBiggerSizeBold);
        containerTopo.add(page, CENTER, CENTER, PREFERRED, PREFERRED);

        containerCliente = new Container();
        containerCliente.borderColor = Colors.GRAY;
        containerCliente.setBorderStyle(Container.BORDER_TOP);

        containerCliente.setInsets(25, 25, 25, 25);
        add(containerCliente, LEFT, AFTER, FILL, PARENTSIZE + 20);

        nome = new Label("" + cliente.getNome());
        nome.setFont(Fonts.sansRegularBiggerSizeBold);
        containerCliente.add(nome, SAME, AFTER, PREFERRED, PREFERRED);

        cpf_cnpj = new Label("CPF/CNPJ: " + cliente.getCpf_cnpj());
        cpf_cnpj.setFont(Fonts.sansRegularDefaultSize);
        containerCliente.add(cpf_cnpj, SAME, AFTER, PREFERRED, PREFERRED);

        telefone = new Label("Telefone: " + cliente.getTelefone());
        telefone.setFont(Fonts.sansRegularDefaultSize);
        containerCliente.add(telefone, SAME, AFTER, PREFERRED, PREFERRED);

        email = new Label("Email: " + cliente.getEmail());
        email.setFont(Fonts.sansRegularDefaultSize);
        containerCliente.add(email, SAME, AFTER, PREFERRED, PREFERRED);


        containerTituloItens = new Container();
        containerTituloItens.setInsets(25, 25, 5, 5);
        add(containerTituloItens, LEFT, AFTER, FILL, PARENTSIZE + 5);

        page = new Label("Pedidos");
        page.setFont(Fonts.sansRegularBiggerSizeBold);
        containerTituloItens.add(page, LEFT, TOP, PREFERRED, PREFERRED);

        btn2 = new Button("+");
        btn2.setBackForeColors(Colors.BLUE_BUTTONS, Colors.WHITE);
        btn2.setBorder(Container.BORDER_ROUNDED);
        btn2.setFont(Fonts.sansIcons);
        containerTituloItens.add(btn2, RIGHT, SAME, 25, 25);
        btn2.addPressListener((e) -> {
            MainWindow.getMainWindow().swap(new ItemPedidoCadWindow(cliente));
        });


        for (Pedido pedido : pedidos) {

            container = new Container();
            container.borderColor = Colors.GRAY;
            container.setBorderStyle(Container.BORDER_TOP);
            add(container, LEFT, AFTER, FILL, 1);

            container = new Container();
            container.setInsets(35, 35, 25, 25);
            add(container, LEFT, AFTER, PARENTSIZE + 60, PARENTSIZE + 22);

            lbl = new Label("Pedido " + pedido.getId() + "");
            lbl.setFont(Fonts.sansRegularBiggerSizeBold);
            container.add(lbl, LEFT, SAME, PREFERRED, PREFERRED);

            lbl = new Label("Data Emissão: " + pedido.getDataEmissao().getSQLString() + "");
            lbl.setFont(Fonts.sansRegularDefaultSize);
            container.add(lbl, LEFT, AFTER, PREFERRED, PREFERRED);

            lbl = new Label("Data de Entrega: " + pedido.getDataEntrega().getSQLString() + "");
            lbl.setFont(Fonts.sansRegularDefaultSize);
            container.add(lbl, LEFT, AFTER, PREFERRED, PREFERRED);

            lbl = new Label("Valor Total: R$ " + pedido.getTotalPedido() + "");
            lbl.setFont(Fonts.sansRegularDefaultSize);
            container.add(lbl, LEFT, AFTER, PREFERRED, PREFERRED);

            lbl = new Label("Status: " + pedido.getStatus());
            lbl.setFont(Fonts.sansRegularDefaultSize);
            container.add(lbl, LEFT, AFTER, PREFERRED, PREFERRED);

            containerActions = new Container();
            containerActions.setInsets(0, 25, 40, 0);
            add(containerActions, RIGHT, SAME, PARENTSIZE + 35, PARENTSIZE + 22);

            btnLapis = new Button(Images.icon_lapis);
            btnLapis.setBorder(Container.BORDER_NONE);
            btnLapis.setFont(Fonts.sansRegularBiggerSizeBold);
            containerActions.add(btnLapis, LEFT, AFTER, PREFERRED, PREFERRED);
            btnLapis.addPressListener((e) -> {
                MainWindow.getMainWindow().swap(new PedidoCadWindow(pedido));
            });

            btnLixeira = new Button(Images.icon_lixeira);
            btnLixeira.setBorder(Container.BORDER_NONE);
            btnLixeira.setFont(Fonts.sansRegularBiggerSizeBold);
            containerActions.add(btnLixeira, RIGHT, SAME, PREFERRED, PREFERRED);
            btnLixeira.addPressListener((e) -> {

                clienteService.deletarPedido(pedido);

                MessageBox mb = new MessageBox("Message", "Pedido excluído com sucesso.", new String[]{"Fechar"});
                mb.popup();

                MainWindow.getMainWindow().swap(new ClienteViewWindow(cliente));

            });

            if (pedido.getStatus().equals("FECHADO")) {
                btnLapis.setEnabled(false);
                btnLixeira.setEnabled(false);
            }

        }
    }
}
