package com.wmw.treinamento.dao;

import com.wmw.treinamento.domain.Cliente;
import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.dto.ClienteDTO;
import com.wmw.treinamento.util.DatabaseConnection;
import totalcross.sql.Connection;
import totalcross.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void inserirCliente(ClienteDTO cliente) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        try {
            dbcon.createStatement().execute("insert into cliente (id, cpf_cnpj, email, nome, telefone, tipo_pessoa) values ("
                    + cliente.getId() + ", '"
                    + cliente.getCpf_cnpj() + "', '"
                    + cliente.getEmail() + "', '"
                    + cliente.getNome() + "', '"
                    + cliente.getTelefone() + "', '"
                    + cliente.getTipoPessoa() + "')");
        } finally {
            dbcon.close();
        }
    }

    public List<Cliente> listarCliente() throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        List<Cliente> clientes = new ArrayList<>();
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select * from cliente");
            while (rsTemp.next()) {
                Cliente cliente = new Cliente(rsTemp);
                clientes.add(cliente);
            }
        } finally {
            dbcon.close();
        }

        return clientes;

    }

    public Cliente detalharCliente(int id) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        Cliente cliente = null;
        Pedido pedido = null;
        List<Pedido> pedidos = new ArrayList<>();
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select * from cliente where id=" + id + "");
            while (rsTemp.next()) {
                 cliente = new Cliente(rsTemp);
            }
            rsTemp = dbcon.createStatement().executeQuery("select * from pedido where id_cliente=" + id + "");
            while (rsTemp.next()) {
                pedidos.add(new Pedido(rsTemp));
                cliente.setPedidos(pedidos);
            }


        } finally {
            dbcon.close();
        }
        return cliente;

    }

    public Boolean buscarCliente(String cpf_cnpj) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select * from cliente where cpf_cnpj='" + cpf_cnpj + "'");
            while (rsTemp.next()) {
                if (rsTemp != null)
                    return true;
            }
        } finally {
            dbcon.close();
        }

        return false;
    }

}
