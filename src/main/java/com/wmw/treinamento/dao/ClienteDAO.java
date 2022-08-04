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
        DatabaseConnection connection = new DatabaseConnection();
        Connection dbcon = connection.getConnection();

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
        DatabaseConnection connection = new DatabaseConnection();
        Connection dbcon = connection.getConnection();

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
        DatabaseConnection connection = new DatabaseConnection();
        Connection dbcon = connection.getConnection();


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

    public int retornaUltimoId() throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection dbcon = connection.getConnection();

        int id = 0;
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select max(id) as id from cliente");
            while (rsTemp.next()) {
                id = rsTemp.getInt("id");
            }
        } finally {
            dbcon.close();
        }

        return id;

    }

}
