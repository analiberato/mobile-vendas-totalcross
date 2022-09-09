package com.wmw.treinamento.dto;

import totalcross.sql.ResultSet;
import totalcross.util.BigDecimal;

import java.sql.SQLException;

public class ItemPedidoDTO {

    private int id;
    private int idProduto;
    private int idPedido;
    private BigDecimal quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal desconto;
    private BigDecimal totalItem;

    public ItemPedidoDTO(ResultSet rsTemp) throws SQLException {
        setId(rsTemp.getInt("id"));
        setIdProduto(rsTemp.getInt("id_produto"));
        setIdPedido(rsTemp.getInt("id_pedido"));
        setQuantidade(rsTemp.getBigDecimal("quantidade"));
        setDesconto(rsTemp.getBigDecimal("desconto"));
        setPrecoUnitario(rsTemp.getBigDecimal("preco_unitario"));
        setTotalItem(rsTemp.getBigDecimal("total_item"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getQuantidade() {
        return String.valueOf(quantidade);
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getPrecoUnitario() {
        return String.valueOf(precoUnitario);
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getDesconto() {
        return String.valueOf(desconto);
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public String getTotalItem() {
        return String.valueOf(totalItem);
    }

    public void setTotalItem(BigDecimal totalItem) {
        this.totalItem = totalItem;
    }
}