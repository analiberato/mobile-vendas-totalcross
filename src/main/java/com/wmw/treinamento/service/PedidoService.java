package com.wmw.treinamento.service;

import com.wmw.treinamento.dao.ProdutoDAO;
import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.domain.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private List<Produto> produtos = new ArrayList<>();

    public PedidoService()  {
        try {
            produtos = produtoDAO.listarProduto();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String retornaListaItens(Pedido pedido) {
        System.out.println("retornaListaItens");

        String items = "";
        for (ItemPedido item : pedido.getItens()) {
            for (Produto produto : produtos) {
                if (produto.getId() == item.getId_produto())
                    items += produto.getNome();
            }
            items += " x" + item.getQuantidade() + "\n";
        }

        return items;
    }

}
