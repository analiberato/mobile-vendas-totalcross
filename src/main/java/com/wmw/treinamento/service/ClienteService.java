package com.wmw.treinamento.service;

import com.wmw.treinamento.dao.ClienteDAO;
import com.wmw.treinamento.dao.PedidoDAO;
import com.wmw.treinamento.domain.Cliente;
import com.wmw.treinamento.domain.Pedido;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {

    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    public Cliente inicializarCliente(Cliente cliente){
        try {
            cliente = clienteDAO.detalharCliente(cliente.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public List<Pedido> inicializarPedidos(Cliente cliente){
        List<Pedido> pedidos;
        try {
            pedidos = pedidoDAO.listarPedidoById(cliente.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }

    public void deletarPedido(Pedido pedido){
        try {
            pedidoDAO.deletarPedido(pedido.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> listarClientes(){
        List<Cliente> clientes;
        try {
            clientes = clienteDAO.listarCliente();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }
}
