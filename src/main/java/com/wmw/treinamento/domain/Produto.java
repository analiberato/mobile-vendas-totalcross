package com.wmw.treinamento.domain;

import totalcross.sql.ResultSet;
import totalcross.util.BigDecimal;

import java.sql.SQLException;

public class Produto {
	private Integer id;
	private String nome;
	private BigDecimal preco;

    public Produto(ResultSet rsTemp) throws SQLException {
		setId(rsTemp.getInt("id"));
		setNome(rsTemp.getString("nome"));
		setPreco(rsTemp.getBigDecimal("preco"));
    }

	public Produto() {
	}

	public Produto(Integer id, String nome, BigDecimal preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


}
