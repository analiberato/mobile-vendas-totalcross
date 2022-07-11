package com.wmw.treinamento.domain.dto;

import com.wmw.treinamento.projetovendas.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

	private Long id;
	private String nome;
	private Double preco;

	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
	}

	public static List<ProdutoDTO> converter(List<Produto> produto) {
		return produto.stream().map(ProdutoDTO::new).collect(Collectors.toList());
	}

}
