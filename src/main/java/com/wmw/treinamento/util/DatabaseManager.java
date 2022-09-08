package com.wmw.treinamento.util;

import totalcross.db.sqlite.SQLiteUtil;
import totalcross.sql.Statement;
import totalcross.sql.Connection;
import totalcross.sys.Settings;

import java.sql.SQLException;

public class DatabaseManager {
    public static SQLiteUtil sqliteUtil;

    static {

        try {

            sqliteUtil = new SQLiteUtil(Settings.appPath, "vendas.db");
            Statement st = sqliteUtil.con().createStatement();

            st.execute("create table if not exists produto " +
                    "(id integer primary key, " +
                    "nome varchar, " +
                    "preco double)");

            st.execute("create table if not exists cliente " +
                    "(id integer primary key, " +
                    "nome varchar not null, " +
                    "tipo_pessoa varchar not null, " +
                    "cpf_cnpj varchar not null, " +
                    "telefone varchar not null, " +
                    "email varchar not null)");

            st.execute("create table if not exists pedido " +
                    "(id integer primary key autoincrement, " +
                    "data_emissao date not null, " +
                    "data_entrega date not null, " +
                    "total_pedido double not null, " +
                    "status varchar TEXT CHECK( status IN ('RASCUNHO','FECHADO') ) not null DEFAULT 'RASCUNHO'," +
                    "sincronizado integer not null DEFAULT 0," +
                    "id_cliente integer," +
                    "FOREIGN KEY(id_cliente) REFERENCES cliente(id))");

            st.execute("create table if not exists item_pedido " +
                    "(id integer primary key autoincrement, " +
                    "quantidade integer not null, " +
                    "desconto double not null, " +
                    "preco_unitario double not null, " +
                    "total_item double not null, " +
                    "id_produto integer," +
                    "id_pedido integer," +
                    "FOREIGN KEY(id_produto) REFERENCES produto(id)," +
                    "FOREIGN KEY(id_pedido) REFERENCES pedido(id))");

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
