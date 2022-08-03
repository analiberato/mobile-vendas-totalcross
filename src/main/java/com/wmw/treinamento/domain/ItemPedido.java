package com.wmw.treinamento.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import totalcross.sql.ResultSet;
import java.sql.SQLException;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {

	private int id;
	private int id_produto;
	private int id_pedido;
	private int quantidade;
	private double precoUnitario;
	private double desconto;
	private double totalItem;

    public ItemPedido(ResultSet rsTemp) throws SQLException {
		setId(rsTemp.getInt("id"));
		setId_produto(rsTemp.getInt("id_produto"));
		setId_pedido(rsTemp.getInt("id_pedido"));
		setQuantidade(rsTemp.getInt("quantidade"));
		setDesconto(rsTemp.getDouble("desconto"));
		setPrecoUnitario(rsTemp.getDouble("preco_unitario"));
		setTotalItem(rsTemp.getDouble("total_item"));
    }

	public ItemPedido(int id_pedido, int id_produto, int quantidade, double preco_unitario, double desconto, double total_item){
		setId_pedido(id_pedido);
		setId_produto(id_produto);
		setQuantidade(quantidade);
		setDesconto(desconto);
		setPrecoUnitario(preco_unitario);
		setTotalItem(total_item);
	}
}