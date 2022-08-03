package com.wmw.treinamento.service;

import com.wmw.treinamento.dao.ProdutoDAO;
import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.domain.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPedidoService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private List<Produto> produtos = new ArrayList<>();

    public ItemPedidoService()  {
        try {
            produtos = produtoDAO.listarProduto();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] retornaListaProdutos() {
        String items[] = new String[produtos.size()];
        int i = 0;
        for (Produto produto : produtos) {
            items[i] = produto.getNome();
            i++;
        }
        return items;
    }

    public ItemPedido calculaQuantidade(ItemPedido item, int quantidade){

        double totalItem = quantidade * item.getPrecoUnitario();
        System.out.println("total " + totalItem);

        item.setQuantidade(quantidade);
        item.setTotalItem(totalItem);

        return item;
    }

    public ItemPedido calculaDesconto(double preco, ItemPedido item, double desconto){

        double precoDesconto = preco - (preco * (desconto * 0.01));
        double totalItem = precoDesconto * item.getQuantidade();

        item.setDesconto(desconto);
        item.setPrecoUnitario(precoDesconto);
        item.setTotalItem(totalItem);

        return item;
    }

}
