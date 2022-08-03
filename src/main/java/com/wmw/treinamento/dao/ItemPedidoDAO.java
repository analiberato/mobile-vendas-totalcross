package com.wmw.treinamento.dao;

import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.util.DatabaseConnection;
import totalcross.sql.Connection;
import totalcross.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoDAO {

    public List<ItemPedido> listarItemById(int id) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection dbcon = connection.getConnection();

        List<ItemPedido> items = new ArrayList<>();
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select * from item_pedido where id_pedido=" + id + "");
            while (rsTemp.next()) {
                ItemPedido item = new ItemPedido(rsTemp);
                items.add(item);
            }
        } finally {
            dbcon.close();
        }

        return items;

    }

    public void inserirItem(ItemPedido itemPedido) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection dbcon = connection.getConnection();

        try {

            dbcon.createStatement().execute("insert into item_pedido (id_pedido, id_produto, quantidade, preco_unitario, desconto, total_item) values ("
                    + itemPedido.getId_pedido() + ", "
                    + itemPedido.getId_produto() + ", "
                    + itemPedido.getQuantidade() + ", "
                    + itemPedido.getPrecoUnitario() + ", "
                    + itemPedido.getDesconto() + ", "
                    + itemPedido.getTotalItem() + ")");
        } finally {
            dbcon.close();
        }
    }

    public int retornaExisteId(Integer id) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection dbcon = connection.getConnection();

        int id_retorno = -1;
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select id as id from item_pedido where id=" + id + "");
            while (rsTemp.next()) {
                id_retorno = rsTemp.getInt("id");
            }
        } finally {
            dbcon.close();
        }

        return id_retorno;

    }

    public void deletarItem(int id) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection dbcon = connection.getConnection();
        try {
            dbcon.createStatement().execute("delete from item_pedido where id=" + id + "");
        } finally {
            dbcon.close();
        }
    }


}
