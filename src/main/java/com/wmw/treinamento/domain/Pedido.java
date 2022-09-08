package com.wmw.treinamento.domain;

import totalcross.sql.ResultSet;
import totalcross.util.BigDecimal;
import totalcross.util.Date;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private Integer id;
	private Integer id_cliente;
	private Date dataEmissao;
	private Date dataEntrega;
	private BigDecimal totalPedido;
	private String status = "RASCUNHO";
	private int sincronizado = 0;
	private List<ItemPedido> itens = new ArrayList<>();

	public Pedido(){
		setTotalPedido(BigDecimal.ZERO);
	}

	public Pedido(ResultSet rs) throws SQLException {
		setId(rs.getInt("id"));
		setId_cliente(rs.getInt("id_cliente"));
		setDataEmissao(rs.getDate("data_emissao"));
		setDataEntrega(rs.getDate("data_entrega"));
		setTotalPedido(rs.getBigDecimal("total_pedido"));
		setStatus(rs.getString("status"));
	}

	public Pedido(int id_cliente, Date data_emissao, Date data_entrega, BigDecimal total_pedido, String status){
		setId_cliente(id_cliente);
		setDataEmissao(data_emissao);
		setDataEntrega(data_entrega);
		setTotalPedido(total_pedido);
		setStatus(status);
	}

	public void addItem(ItemPedido itemPedido){
		itens.add(itemPedido);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public BigDecimal getTotalPedido() {
		return totalPedido;
	}

	public void setTotalPedido(BigDecimal totalPedido) {
		totalPedido = totalPedido.setScale(2, BigDecimal.ROUND_DOWN);
		this.totalPedido = totalPedido;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public int getSincronizado() {
		return sincronizado;
	}

	public void setSincronizado(int sincronizado) {
		this.sincronizado = sincronizado;
	}
}