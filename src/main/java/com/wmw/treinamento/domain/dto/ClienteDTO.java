package com.wmw.treinamento.domain.dto;

import com.wmw.treinamento.projetovendas.model.Cliente;
import com.wmw.treinamento.projetovendas.model.TipoPessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private Long id;

	@NotBlank(message = "Por favor insira seu nome")
	private String nome;

	@NotBlank(message = "Por favor insira o tipo físico ou jurídico")
	private TipoPessoa tipoPessoa;

	@CPF
	@CNPJ
	@NotBlank(message = "Por favor insira seu cpf ou cnpj")
	private String cpf_cnpj;

	@Pattern(regexp = "(^$|[0-9]{10})")
	@NotBlank(message = "Por favor insira seu telefone")
	private String telefone;

	@Email
	@NotBlank(message = "Por favor insira seu email")
	private String email;

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.tipoPessoa = cliente.getTipoPessoa();
		this.cpf_cnpj = cliente.getCpf_cnpj();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
	}

	public static List<ClienteDTO> converter(List<Cliente> cliente) {
		return cliente.stream().map(ClienteDTO::new).collect(Collectors.toList());
	}

}
