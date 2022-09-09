import com.wmw.treinamento.domain.Cliente;
import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.domain.Produto;
import com.wmw.treinamento.service.ItemPedidoService;
import com.wmw.treinamento.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import totalcross.sys.InvalidNumberException;
import totalcross.util.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestesUnitarios {

    private PedidoService pedidoService;
    private ItemPedidoService itemPedidoService;
    private List<Produto> produtos = new ArrayList<>();

    @BeforeEach
    public void inicializa() {
        this.pedidoService = new PedidoService();
        this.itemPedidoService = new ItemPedidoService();
    }

    @Test
    void deveriaRetornaDescontoItem() {
        ItemPedido item = itemPedidoService.calculaDesconto("5.0", criarItem().getItens().get(0));
        assertEquals(31.35, item.getPrecoUnitario());
    }

    @Test
    void totalDoPedidoDeveriaSerIgualSomaDosItens() {
        Pedido pedido = criarItem();
        pedido = itemPedidoService.adicionaItemSomaTotal(pedido, pedido.getItens().get(0));
        try {
            assertEquals(new BigDecimal("165.00"), pedido.getTotalPedido());
        } catch (InvalidNumberException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deveriaRetornarTotalDoItem() {
        ItemPedido item = itemPedidoService.calculaTotalItem(criarItem().getItens().get(0), new BigDecimal(5));
        assertEquals(165.0, item.getTotalItem());
    }

    @Test
    void deveriaDevolverFalsoQueNaoTemUmItem() {
        Boolean resultado = pedidoService.verificaSeTemMinimoUmItem(criarPedido("2022-12-04"));
        assertEquals(false, resultado);
    }

    @Test
    void deveriaDevolverVerdadeiroQueTemUmItem() {
        Boolean resultado = pedidoService.verificaSeTemMinimoUmItem(criarItem());
        assertEquals(true, resultado);
    }

    @Test
    void totalDoPedidoDeveriaSerRetornarFalsoDataAnterior() {
        Boolean resultado = pedidoService.verificarDataFutura("2022-04-04");
        assertEquals(false, resultado);
    }

    @Test
    void totalDoPedidoDeveriaSerRetornarVerdadeiroDataCorreta() {
        Boolean resultado = pedidoService.verificarDataFutura("2022-12-04");
        assertEquals(true, resultado);
    }
    private Pedido criarPedido(String dataEntrega) {
        Pedido pedido = new Pedido();
        pedido.setId_cliente(1);
        pedidoService.setDataEmissao(pedido);
        pedidoService.setDataEntrega(pedido, dataEntrega);
        return pedido;
    }

    private void criarProduto() {
        try {
            produtos.add(new Produto(new Integer(1), "Nivea Noturno", new BigDecimal(2.0)));
            produtos.add(new Produto(new Integer(2), "Perfume Shakira", new BigDecimal(89.90)));
            produtos.add(new Produto(new Integer(3), "Lip Chilli FR", new BigDecimal(39.90)));
        } catch (InvalidNumberException e) {
            throw new RuntimeException(e);
        }
    }

    private Pedido criarItem() {
        Pedido pedido = criarPedido("2022-12-04");
        try {
            pedido.getItens().add(new ItemPedido(1, 1, new BigDecimal(5.0), new BigDecimal(33.00), new BigDecimal(5.0), new BigDecimal(165.00)));
        } catch (InvalidNumberException e) {
            throw new RuntimeException(e);
        }
        return pedido;
    }

}