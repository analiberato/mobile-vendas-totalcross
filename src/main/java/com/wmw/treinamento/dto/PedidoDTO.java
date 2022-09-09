package com.wmw.treinamento.dto;

import totalcross.sql.ResultSet;
import totalcross.util.BigDecimal;
import totalcross.util.Date;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {

    private Integer id;
    private Integer idCliente;
    private Date dataEmissao;
    private Date dataEntrega;
    private BigDecimal totalPedido;
    private String status;
    private int sincronizado = 0;
    private List<ItemPedidoDTO> itens = new ArrayList<>();

    public PedidoDTO(ResultSet rs) throws SQLException {
        setId(rs.getInt("id"));
        setIdCliente(rs.getInt("id_cliente"));
        setDataEmissao(rs.getDate("data_emissao"));
        setDataEntrega(rs.getDate("data_entrega"));
        setTotalPedido(rs.getBigDecimal("total_pedido"));
        setStatus(rs.getString("status"));
    }
    public void addItem(ItemPedidoDTO itemPedidoDTO){
        itens.add(itemPedidoDTO);
    }
    public void addItems(List<ItemPedidoDTO> itemPedidoDTOS){
        itens.addAll(itemPedidoDTOS);
    }

    public String getDataEmissao() {
        return dataEmissao.getSQLString();
    }

    public String getDataEntrega() {
        return dataEntrega.getSQLString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getTotalPedido() {
        totalPedido.setScale(2, BigDecimal.ROUND_DOWN);
        return totalPedido.toString();
    }

    public void setTotalPedido(BigDecimal totalPedido) { this.totalPedido = totalPedido; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(int sincronizado) {
        this.sincronizado = sincronizado;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PedidoDTO{");
        sb.append("id=").append(id);
        sb.append(", id_cliente=").append(idCliente);
        sb.append(", dataEmissao=").append(dataEmissao);
        sb.append(", dataEntrega=").append(dataEntrega);
        sb.append(", totalPedido=").append(totalPedido);
        sb.append(", status='").append(status).append('\'');
        sb.append(", sincronizado=").append(sincronizado);
        sb.append(", itens=").append(itens);
        sb.append('}');
        return sb.toString();
    }
}