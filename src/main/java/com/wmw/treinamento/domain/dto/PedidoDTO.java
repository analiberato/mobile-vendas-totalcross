package com.wmw.treinamento.domain.dto;

import com.wmw.treinamento.projetovendas.model.Pedido;
import com.wmw.treinamento.projetovendas.model.StatusPedido;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

	private Long id;
	private Long idCliente;
	private LocalDate dataEmissao;
	private LocalDate dataEntrega;
	@Positive
	private Double totalPedido;
	private StatusPedido status;
	private List<ItemPedidoDTO> itens = new ArrayList<>();

	public static List<PedidoDTO> converter(List<Pedido> pedido) {
		return pedido.stream().map(PedidoDTO::new).collect(Collectors.toList());
	}

	public PedidoDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.idCliente = pedido.getCliente().getId();
		this.dataEmissao = pedido.getDataEmissao();
		this.dataEntrega = pedido.getDataEntrega();
		this.totalPedido = pedido.getTotalPedido();
		this.status = pedido.getStatus();
		this.itens.addAll(pedido.getItens().stream().map(ItemPedidoDTO::new).collect(Collectors.toList()));
	}

}
