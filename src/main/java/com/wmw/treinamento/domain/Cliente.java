package com.wmw.treinamento.domain;

import totalcross.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private Integer id;
	private String nome;
	private String tipoPessoa;
	private String cpf_cnpj;
	private String telefone;
	private String email;
	private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(ResultSet rsTemp) throws SQLException {
		setId(rsTemp.getInt("id"));
		setNome(rsTemp.getString("nome"));
		setTipoPessoa(rsTemp.getString("tipo_pessoa"));
		setCpf_cnpj(rsTemp.getString("cpf_cnpj"));
		setTelefone(rsTemp.getString("telefone"));
		setEmail(rsTemp.getString("email"));
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

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
