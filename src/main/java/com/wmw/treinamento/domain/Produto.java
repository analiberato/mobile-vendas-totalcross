package com.wmw.treinamento.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import totalcross.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	private Integer id;
	private String nome;
	private double preco;
	private List<ItemPedido> itens = new ArrayList<>();

    public Produto(ResultSet rsTemp) throws SQLException {
		setId(rsTemp.getInt("id"));
		setNome(rsTemp.getString("nome"));
		setPreco(rsTemp.getDouble("preco"));
    }
}
