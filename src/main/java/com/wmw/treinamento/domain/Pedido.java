package com.wmw.treinamento.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import totalcross.sql.ResultSet;
import totalcross.util.Date;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

	private Integer id;
	private Integer id_cliente;
	private Date dataEmissao;
	private Date dataEntrega;
	private double totalPedido;
	private String status = "RASCUNHO";
	private List<ItemPedido> itens = new ArrayList<>();

	public Pedido(ResultSet rs) throws SQLException {
		setId(rs.getInt("id"));
		setId_cliente(rs.getInt("id_cliente"));
		setDataEmissao(rs.getDate("data_emissao"));
		setDataEntrega(rs.getDate("data_entrega"));
		setTotalPedido(rs.getDouble("total_pedido"));
		setStatus(rs.getString("status"));
	}

	public Pedido(int id_cliente, Date data_emissao, Date data_entrega, double total_pedido, String status){
		setId_cliente(id_cliente);
		setDataEmissao(data_emissao);
		setDataEntrega(data_entrega);
		setTotalPedido(total_pedido);
		setStatus(status);
	}

	public void addItem(ItemPedido itemPedido){
		itens.add(itemPedido);
	}
}