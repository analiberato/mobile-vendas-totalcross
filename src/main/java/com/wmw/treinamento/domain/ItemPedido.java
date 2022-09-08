package com.wmw.treinamento.domain;

import totalcross.sql.ResultSet;
import totalcross.sys.InvalidNumberException;
import totalcross.util.BigDecimal;

import java.sql.SQLException;

public class ItemPedido {

	private int id;
	private int id_produto;
	private int id_pedido;
	private BigDecimal quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal desconto;
	private BigDecimal totalItem;

	public ItemPedido(){
		setQuantidade(BigDecimal.ZERO);
		setDesconto(BigDecimal.ZERO);
		setTotalItem(BigDecimal.ZERO);
	}

    public ItemPedido(ResultSet rsTemp) throws SQLException {
		setId(rsTemp.getInt("id"));
		setId_produto(rsTemp.getInt("id_produto"));
		setId_pedido(rsTemp.getInt("id_pedido"));
		setQuantidade(rsTemp.getBigDecimal("quantidade"));
		setDesconto(rsTemp.getBigDecimal("desconto"));
		setPrecoUnitario(rsTemp.getBigDecimal("preco_unitario"));
		setTotalItem(rsTemp.getBigDecimal("total_item"));
    }

	public ItemPedido(int id_pedido, int id_produto, BigDecimal quantidade, BigDecimal preco_unitario, BigDecimal desconto, BigDecimal total_item){
		setId_pedido(id_pedido);
		setId_produto(id_produto);
		setQuantidade(quantidade);
		setDesconto(desconto);
		setPrecoUnitario(preco_unitario);
		setTotalItem(total_item);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Double getQuantidade() {
		return Double.parseDouble(String.valueOf(quantidade));
	}

	public void setQuantidade(BigDecimal quantidade) {
		quantidade = quantidade.setScale(1, BigDecimal.ROUND_DOWN);
		this.quantidade = quantidade;
	}

	public Double getPrecoUnitario() {
		return Double.parseDouble(String.valueOf(precoUnitario));
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		precoUnitario = precoUnitario.setScale(2, BigDecimal.ROUND_DOWN);
		this.precoUnitario = precoUnitario;
	}

	public Double getDesconto() {
		return Double.parseDouble(String.valueOf(desconto));
	}

	public void setDesconto(BigDecimal desconto) {
		desconto = desconto.setScale(2, BigDecimal.ROUND_DOWN);
		this.desconto = desconto;
	}

	public Double getTotalItem() {
		return Double.parseDouble(String.valueOf(totalItem));
	}

	public void setTotalItem(BigDecimal totalItem) {
		totalItem = totalItem.setScale(2, BigDecimal.ROUND_DOWN);
		this.totalItem = totalItem;
	}


}