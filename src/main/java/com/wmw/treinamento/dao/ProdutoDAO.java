package com.wmw.treinamento.dao;

import com.wmw.treinamento.domain.Produto;
import com.wmw.treinamento.util.DatabaseConnection;
import totalcross.sql.Connection;
import totalcross.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public List<Produto> listarProduto() throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection dbcon = connection.getConnection();

        List<Produto> produtos = new ArrayList<>();
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select * from produto");
            while (rsTemp.next()) {
                Produto produto = new Produto(rsTemp);
                produtos.add(produto);
            }
        } finally {
            dbcon.close();
        }

        return produtos;

    }

    public Produto detalharProdutoByNome(String nome) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection dbcon = connection.getConnection();

        Produto produto = null;
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select * from produto where nome='" + nome + "'");
            while (rsTemp.next()) {
                produto = new Produto(rsTemp);
            }
        } finally {
            dbcon.close();
        }

        return produto;

    }

    public String nomeProduto(int id) throws SQLException {
        DatabaseConnection connection = new DatabaseConnection();
        Connection dbcon = connection.getConnection();

        String nome = null;
        ResultSet rsTemp = null;
        try {
            rsTemp = dbcon.createStatement().executeQuery("select nome from produto where id='" + id + "'");
            while (rsTemp.next()) {
                nome = rsTemp.getString("nome");
            }
        } finally {
            dbcon.close();
        }

        return nome;

    }

}
