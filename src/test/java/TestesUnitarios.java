import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.service.ItemPedidoService;
import com.wmw.treinamento.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TestesUnitarios {

    private PedidoService pedidoService;
    private ItemPedidoService itemPedidoService;

    @BeforeEach
    public void inicializa() {
        this.pedidoService = new PedidoService();
        this.itemPedidoService = new ItemPedidoService();
    }

//    @Test
//    void deveriaRetornaDescontoItem() {
//        try {
//            ItemPedido item = new ItemPedido(1, 1, 10, double preco_unitario, double desconto, double total_item);
//            itemPedidoService.calculaDesconto(33.90, ); //preco, ItemPedido item, double desconto
//            fail("nao deu exception");
//        } catch (IllegalArgumentException e) {
//            assertEquals("Funcionario com salario maior do que R$1000 nao pode receber bonus!", e.getMessage());
//        }
//
//    }

    //assertEquals é o valor esperado
//    @Test
//    void totalDoPedidoDeveriaSerIgualSomaDosItens() {
//        try {
//            pedidoService.calcularBonus(criarFuncionario(new BigDecimal("25000")));
//            fail("nao deu exception");
//        } catch (IllegalArgumentException e) {
//            assertEquals("Funcionario com salario maior do que R$1000 nao pode receber bonus!", e.getMessage());
//        }
//
//    }

}
