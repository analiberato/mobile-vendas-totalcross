package com.wmw.treinamento.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import totalcross.sql.ResultSet;
import totalcross.util.Properties.Long;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
