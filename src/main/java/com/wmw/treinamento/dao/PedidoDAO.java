package com.wmw.treinamento.dao;

import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.dto.PedidoDTO;
import com.wmw.treinamento.util.DatabaseConnection;
import totalcross.sql.Connection;
import totalcross.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public List<PedidoDTO> listarPedidoDTO() throws SQLException {
        List<PedidoDTO> pedidos = new ArrayList<>();
        Connection dbcon = DatabaseConnection.getConnection();
        try {
            ResultSet rsTemp = dbcon.createStatement().executeQuery("select * from pedido");
            try {
                while (rsTemp.next()) {
                    PedidoDTO pedido = new PedidoDTO(rsTemp);
                    pedidos.add(pedido);
                }
            } finally {
                rsTemp.close();
            }
        } finally {
            dbcon.close();
        }

        return pedidos;

    }

    public List<PedidoDTO> listarPedidoDTONaoSincronizados() throws SQLException {
        List<PedidoDTO> pedidos = new ArrayList<>();
        Connection dbcon = DatabaseConnection.getConnection();
        try {
            ResultSet rsTemp = dbcon.createStatement().executeQuery("select * from pedido where sincronizado=0");
            try {
                while (rsTemp.next()) {
                    PedidoDTO pedido = new PedidoDTO(rsTemp);
                    pedidos.add(pedido);
                }
            } finally {
                rsTemp.close();
            }
        } finally {
            dbcon.close();
        }

        return pedidos;

    }

    public List<Pedido> listarPedidoById(int id) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        List<Pedido> pedidos = new ArrayList<>();
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select * from pedido where id_cliente=" + id + "");
            while (rsTemp.next()) {
                Pedido pedido = new Pedido(rsTemp);
                pedidos.add(pedido);
            }
        } finally {
            dbcon.close();
        }

        return pedidos;

    }

    public Pedido detalharPedido(int id) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        Pedido pedido = null;
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select * from pedido where id=" + id + "");
            while (rsTemp.next()) {
                pedido = new Pedido(rsTemp);
            }
        } finally {
            dbcon.close();
        }

        return pedido;

    }

    public int retornaUltimoId() throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        int id = 0;
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select max(id) as id from pedido");
            while (rsTemp.next()) {
                id = rsTemp.getInt("id");
            }
        } finally {
            dbcon.close();
        }

        return id;

    }

    public void sincronizar(int id) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        ResultSet rsTemp = null;
        try {
            dbcon.createStatement().execute("update pedido set sincronizado="
                    + 1 + " where id=" + id + "");
        } finally {
            dbcon.close();
        }

    }

    public int retornaExisteId(Integer id) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        int id_retorno = -1;
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select id as id from pedido where id=" + id + "");
            while (rsTemp.next()) {
                id_retorno = rsTemp.getInt("id");
            }
        } finally {
            dbcon.close();
        }

        return id_retorno;

    }

    public void inserirPedido(Pedido pedido) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        try {
            dbcon.createStatement().execute("insert into pedido (id_cliente, data_emissao, data_entrega, total_pedido, status) values ("
                    + pedido.getId_cliente() + ", '"
                    + pedido.getDataEmissao().getSQLString() + "', '"
                    + pedido.getDataEntrega().getSQLString() + "', "
                    + pedido.getTotalPedido() + ", '"
                    + pedido.getStatus() + "')");
        } finally {
            dbcon.close();
        }
    }

    public void inserirPedido(PedidoDTO pedido) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        try {
            dbcon.createStatement().execute("insert into pedido (id_cliente, data_emissao, data_entrega, total_pedido, status) values ("
                    + pedido.getIdCliente() + ", '"
                    + pedido.getDataEmissao() + "', '"
                    + pedido.getDataEntrega() + "', "
                    + pedido.getTotalPedido() + ", '"
                    + pedido.getStatus() + "')");
        } finally {
            dbcon.close();
        }
    }
    public void fecharPedido(Pedido pedido) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        try {
            dbcon.createStatement().execute("update pedido set status='"
                    + pedido.getStatus()
                    + "' where id=" + pedido.getId() + "");
        } finally {
            dbcon.close();
        }
    }

    public void atualizarPedido(Pedido pedido) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();

        try {
            dbcon.createStatement().execute("update pedido set data_entrega='"
                    + pedido.getDataEntrega().getSQLString()
                    + "', total_pedido="
                    + pedido.getTotalPedido()
                    + ", status='"
                    + pedido.getStatus()
                    + "' where id=" + pedido.getId() + "");
        } finally {
            dbcon.close();
        }
    }

    public void deletarPedido(int id) throws SQLException {
        Connection dbcon = DatabaseConnection.getConnection();
        try {
            dbcon.createStatement().execute("delete from pedido where id=" + id + "");
        } finally {
            dbcon.close();
        }
    }

}
