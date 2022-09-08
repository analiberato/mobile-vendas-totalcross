package com.wmw.treinamento.service;

import com.wmw.treinamento.dao.ProdutoDAO;
import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.domain.Produto;
import totalcross.sys.InvalidNumberException;
import totalcross.ui.dialog.MessageBox;
import totalcross.util.BigDecimal;

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

    public ItemPedido calculaQuantidade(ItemPedido item, BigDecimal quantidade) throws InvalidNumberException {

        BigDecimal totalItem = quantidade.multiply(new BigDecimal(item.getPrecoUnitario()));

        item.setQuantidade(quantidade);
        item.setTotalItem(totalItem);

        return item;
    }

    public ItemPedido calculaDesconto(String desconto, BigDecimal preco, ItemPedido item) throws InvalidNumberException {

        item.setDesconto(BigDecimal.valueOf(Double.valueOf(desconto)));

        BigDecimal precoDesconto = preco.subtract(preco.multiply(BigDecimal.valueOf(item.getDesconto()).multiply(BigDecimal.valueOf(0.01))));
        BigDecimal totalItem = precoDesconto.multiply(new BigDecimal(item.getQuantidade()));

        item.setPrecoUnitario(precoDesconto);
        item.setTotalItem(totalItem);

        return item;
    }

    public void adicionaItem(Pedido pedido, ItemPedido item){
        pedido.addItem(item);
        try {
            if(pedido.getTotalPedido() == null)
                pedido.setTotalPedido(new BigDecimal(0.0));
            pedido.setTotalPedido(pedido.getTotalPedido().add(new BigDecimal(item.getTotalItem())));
        } catch (InvalidNumberException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean verificaQuantidadePedido(ItemPedido item){
        if (item.getQuantidade() > 1) {
            return true;
        } else {
            MessageBox mbQuantidade = new MessageBox("Messagem", "Insira um valor válido! Quantidade do produto tem que ser maior que 1.", new String[] { "Fechar" });
            mbQuantidade.popup();
            return false;
        }
    }

    public boolean verificaItem(ItemPedido item){
        if (item != null) {
            return true;
        } else {
            MessageBox mbItem = new MessageBox("Messagem", "Insira um produto.", new String[] { "Fechar" });
            mbItem.popup();
            return false;
        }
    }
}
