package com.wmw.treinamento.dto;

import com.wmw.treinamento.domain.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteDTO {

    private Integer id;
    private String nome;
    private String tipoPessoa;
    private String cpf_cnpj;
    private String telefone;
    private String email;
    private List<Pedido> pedidos = new ArrayList<>();

}