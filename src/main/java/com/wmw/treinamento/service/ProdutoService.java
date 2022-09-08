package com.wmw.treinamento.service;

import com.wmw.treinamento.dao.ProdutoDAO;
import com.wmw.treinamento.domain.Produto;

import java.sql.SQLException;
import java.util.List;

public class ProdutoService {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public List<Produto> listarProdutos(){
        List<Produto> produtos;
        try {
            produtos = produtoDAO.listarProduto();
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }

}
