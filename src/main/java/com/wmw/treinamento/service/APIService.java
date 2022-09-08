package com.wmw.treinamento.service;

import com.wmw.treinamento.api.ClienteAPI;
import com.wmw.treinamento.api.PedidoAPI;
import com.wmw.treinamento.api.ProdutoAPI;
import com.wmw.treinamento.util.DatabaseConnection;
import totalcross.sql.Connection;
import totalcross.ui.dialog.MessageBox;

import java.sql.SQLException;

public class APIService {
    MessageBox mb;
    public void execute(){
        try {
            Connection dbcon = DatabaseConnection.getConnection();

            int clienteResponse = new ClienteAPI().getPressListener();
            int produtoResponse = new ProdutoAPI().getPressListener();
            int pedidoResponse = new PedidoAPI().getPressListener();

            if (clienteResponse == 200 && produtoResponse == 200 && pedidoResponse == 200) {
                mb = new MessageBox("Message", "Dados sincronizados com sucesso.", new String[]{"Fechar"});
            } else {
                mb = new MessageBox("Message", "Dados não sincronizados. Servidor indisponível.", new String[]{"Fechar"});
            }

            mb.popup();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
