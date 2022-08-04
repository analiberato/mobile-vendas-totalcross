package com.wmw.treinamento.dto;

import com.wmw.treinamento.domain.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import totalcross.sql.ResultSet;
import totalcross.util.Date;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private Integer id;
    private Integer id_cliente;

    private Integer idCliente;

    private Date dataEmissao;
    private Date dataEntrega;
    private double totalPedido;
    private String status = "RASCUNHO";
    private int sincronizado = 0;
    private List<ItemPedido> itens = new ArrayList<>();

    public PedidoDTO(ResultSet rs) throws SQLException {
        setId(rs.getInt("id"));
        setId_cliente(rs.getInt("id_cliente"));
        setIdCliente(rs.getInt("id_cliente"));
        setDataEmissao(rs.getDate("data_emissao"));
        setDataEntrega(rs.getDate("data_entrega"));
        setTotalPedido(rs.getDouble("total_pedido"));
        setStatus(rs.getString("status"));
    }
    public void addItem(ItemPedido itemPedido){
        itens.add(itemPedido);
    }
    public void addItems(List<ItemPedido> itemPedido){
        itens.addAll(itemPedido);
    }

    public String getDataEmissao() {
        return dataEmissao.getSQLString();
    }

    public String getDataEntrega() {
        return dataEntrega.getSQLString();
    }

}