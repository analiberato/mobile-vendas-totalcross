package com.wmw.treinamento.domain.dto;

import com.wmw.treinamento.projetovendas.model.ItemPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {

	private Long id;
	@NotBlank
	private Long idProduto;
	@NotBlank
	@Positive
	private Integer quantidade;
	private Double precoUnitario;
	private Double desconto;
	private Double totalItem;

	public ItemPedidoDTO(ItemPedido itemPedido) {
		this.id = itemPedido.getId();
		this.idProduto = itemPedido.getProduto().getId();
		this.quantidade = itemPedido.getQuantidade();
		this.precoUnitario = itemPedido.getPrecoUnitario();
		this.desconto = itemPedido.getDesconto();
		this.totalItem = itemPedido.getTotalItem();
	}

	public static List<ItemPedidoDTO> converter(List<ItemPedido> itens) {
		return itens.stream().map(ItemPedidoDTO::new).collect(Collectors.toList());
	}
}
