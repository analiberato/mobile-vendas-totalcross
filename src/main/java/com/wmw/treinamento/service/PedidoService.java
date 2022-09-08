package com.wmw.treinamento.service;

import com.wmw.treinamento.dao.ItemPedidoDAO;
import com.wmw.treinamento.dao.PedidoDAO;
import com.wmw.treinamento.dao.ProdutoDAO;
import com.wmw.treinamento.domain.ItemPedido;
import com.wmw.treinamento.domain.Pedido;
import com.wmw.treinamento.domain.Produto;
import totalcross.sys.Settings;
import totalcross.ui.dialog.MessageBox;
import totalcross.util.Date;
import totalcross.util.InvalidDateException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PedidoService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
    private List<Produto> produtos = new ArrayList<>();
    private Date hoje = new Date();

    public PedidoService() {
        try {
            produtos = produtoDAO.listarProduto();
            hoje.set(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()), Settings.DATE_YMD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvalidDateException e) {
            throw new RuntimeException(e);
        }
    }

    public Pedido inicializarPedido(Pedido pedido) {
        PedidoDAO pedidoDAO = new PedidoDAO();
        try {
            pedido = pedidoDAO.detalharPedido(pedido.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedido;
    }

    public List<ItemPedido> retornaListaItens(Pedido pedido) {
        List<ItemPedido> itens = new ArrayList<>();
        ItemPedidoDAO itemPedidoDAO = new ItemPedidoDAO();
        try {
            if (pedido.getItens().isEmpty()) {
                itens = itemPedidoDAO.listarItemById(pedido.getId());
            } else {
                for (ItemPedido item: pedido.getItens()) {
                    itens.add(item);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itens;
    }

    public String retornaListaProdutos(Pedido pedido) {
        String items = "";
        for (ItemPedido item : pedido.getItens()) {
            for (Produto produto : produtos) {
                if (produto.getId() == item.getId_produto())
                    items += produto.getNome();
            }
            items += " x" + item.getQuantidade() + "\n";
        }

        return items;
    }

    public boolean verificaSeTemMinimoUmItem(Pedido pedido) {
        if (pedido.getItens().size() > 0) {
            pedido.setStatus("FECHADO");
            try {
                pedidoDAO.fecharPedido(pedido);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return true;
        } else {
            MessageBox mb = new MessageBox("Message", "Pedido precisa de no mínimo 1 item.", new String[]{"Fechar"});
            mb.popup();
            return false;
        }
    }

    public Pedido atualizarPedido(Pedido pedido) {
        try {
            if (pedidoDAO.retornaExisteId(pedido.getId()) != -1){
                pedidoDAO.atualizarPedido(pedido);
                for (ItemPedido item: pedido.getItens()) {
                    if (itemPedidoDAO.retornaExisteId(item.getId()) == -1){
                        item.setId_pedido(pedido.getId());
                        itemPedidoDAO.inserirItem(item);
                    }
                }
            } else {
                pedidoDAO.inserirPedido(pedido);
                pedido.setId(pedidoDAO.retornaUltimoId());
                for (ItemPedido item: pedido.getItens()) {
                    item.setId_pedido(pedido.getId());
                    itemPedidoDAO.inserirItem(item);
                }
            }
            pedido.getItens().clear();
            pedido.getItens().addAll(itemPedidoDAO.listarItemById(pedido.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pedido;
    }

    public Boolean verificarDataFutura(String data) {
        Date date = new Date();
        try {
            date.set(data, Settings.DATE_YMD);
        } catch (InvalidDateException e) {
            throw new RuntimeException(e);
        }
        return date.isAfter(hoje);
    }

    public Pedido setDataEmissao(Pedido pedido) {
        if (pedido.getDataEmissao() == null) {
            Date date_emissao = hoje;
            pedido.setDataEmissao(date_emissao);
        }
        return pedido;
    }

    public Pedido setDataEntrega(Pedido pedido, String dataEntrega) {
        Date date_entrega = new Date();
        Date hoje = new Date();

        try {
            hoje.set(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()), Settings.DATE_YMD);
            date_entrega.set(dataEntrega, Settings.DATE_YMD);
            pedido.setDataEntrega(date_entrega);

        } catch (InvalidDateException ex) {
            throw new RuntimeException(ex);
        }
        return pedido;
    }

    public Boolean verificaDataEntrega(String dataEntrega, Pedido pedido) {
        if (dataEntrega.isEmpty()) {
            MessageBox mb = new MessageBox("Message", "Insira uma data!", new String[]{"Fechar"});
            mb.popup();
            return false;
        } else if (verificarDataFutura(dataEntrega)) {
            pedido = setDataEmissao(pedido);
            pedido = setDataEntrega(pedido, dataEntrega);
            atualizarPedido(pedido);
            return true;
        } else {
            MessageBox mb = new MessageBox("Message", "Data Inválida! Digite uma data futura.", new String[]{"Fechar"});
            mb.popup();
            return false;
        }
    }

    public void adicionarItens(Pedido pedido, List<ItemPedido> itens) {
        try {
            if (pedido.getItens().isEmpty()) {
                itens = itemPedidoDAO.listarItemById(pedido.getId());
                pedido = pedidoDAO.detalharPedido(pedido.getId());
                for (ItemPedido item: itens) {
                    pedido.getItens().add(item);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Pedido deletarItem(int id, Pedido pedido, ItemPedido itemPedido) {
        try {
            if (itemPedidoDAO.retornaExisteId(id) != -1) {
                pedido.getItens().remove(itemPedido);
                itemPedidoDAO.deletarItem(id);
                MessageBox mb = new MessageBox("Message", "Item excluído com sucesso.", new String[]{"Fechar"});
                mb.popup();
                return pedido;
            } else {
                MessageBox mb = new MessageBox("Message", "ERRO: Item não excluído.", new String[]{"Fechar"});
                mb.popup();
                return pedido;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
