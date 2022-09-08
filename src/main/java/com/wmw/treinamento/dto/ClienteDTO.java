package com.wmw.treinamento.dto;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {

    private Integer id;
    private String nome;
    private String tipoPessoa;
    private String cpf_cnpj;
    private String telefone;
    private String email;
    private List<PedidoDTO> pedidos = new ArrayList<>();

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

    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ClienteDTO{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", tipoPessoa='").append(tipoPessoa).append('\'');
        sb.append(", cpf_cnpj='").append(cpf_cnpj).append('\'');
        sb.append(", telefone='").append(telefone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", pedidos=").append(pedidos);
        sb.append('}');
        return sb.toString();
    }
}