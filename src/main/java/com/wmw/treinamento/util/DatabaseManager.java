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
                    "tipo_pessoa varchar not null, " + //TEXT CHECK( tipo_pessoa IN ('FISICA','JURIDICA') ) not null, " +
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


//            st.execute("DELETE FROM cliente");
//            st.execute("DELETE FROM produto");
//            st.execute("DELETE FROM item_pedido");
//            st.execute("DELETE FROM pedido");

            //dados mock
//            st.execute(" INSERT INTO CLIENTE (id, nome, tipo_pessoa, cpf_cnpj, telefone, email) " +
//            "VALUES ('1', 'Ana', 'FISICA', '000.000.000-04', '48984663454', 'ana@wmw.com.br')");
//            st.execute("INSERT INTO CLIENTE (id, nome, tipo_pessoa, cpf_cnpj, telefone, email) " +
//            "VALUES ('2', 'Cesar', 'FISICA', '000.000.000-04', '48984663454', 'cesar@wmw.com.br')");
//            st.execute(" INSERT INTO CLIENTE (id, nome, tipo_pessoa, cpf_cnpj, telefone, email) " +
//            "VALUES ('3', 'Lucas', 'FISICA', '000.000.000-04', '48984663454', 'lucas@wmw.com.br')");
//            st.execute(" INSERT INTO CLIENTE (id, nome, tipo_pessoa, cpf_cnpj, telefone, email) " +
//            "VALUES ('4', 'WMW SYSTEMS', 'JURIDICA', '000.000.000-04', '48984663454','contato@wmw.com.br')");
//
//            st.execute("INSERT INTO PRODUTO (id, nome, preco) " +
//            "VALUES ('1', 'Nivea Noturno', 22.0)");
//            st.execute("INSERT INTO PRODUTO (id, nome, preco) " +
//            "VALUES ('2', 'Else Hidratação Noturna', 19.90)");
//            st.execute("INSERT INTO PRODUTO (id, nome, preco) " +
//            "VALUES ('3', 'Perfume Shakira', 89.90)");
//            st.execute("INSERT INTO PRODUTO (id, nome, preco) " +
//            "VALUES ('4', 'Creme HidraMais', 19.90)");
//            st.execute("INSERT INTO PRODUTO (id, nome, preco) " +
//            "VALUES ('5', 'LipChilli FR', 35.90)");
//            st.execute("INSERT INTO PRODUTO (id, nome, preco) " +
//            "VALUES ('6', 'Shampoo Elseve Intenso', 39.90)");

//            st.execute("INSERT INTO PEDIDO (id, id_cliente, data_emissao, data_entrega, total_pedido, status) " +
//            "VALUES ('1', 1, '2022-05-07', '2022-05-07', 97.84, 'FECHADO')");
//
//            st.execute("INSERT INTO ITEM_PEDIDO (id, id_pedido, id_produto, quantidade, preco_unitario, desconto, total_item) " +
//            "VALUES ('1', 1, 1, 3, 22.00, 0, 66.00)");
//            st.execute("INSERT INTO ITEM_PEDIDO (id, id_pedido, id_produto, quantidade, preco_unitario, desconto, total_item) " +
//            "VALUES ('2', 1, 2, 4, 19.90, 0.4, 31.84)");
//
//            st.execute("INSERT INTO PEDIDO (id, id_cliente, data_emissao, data_entrega, total_pedido, status) " +
//            "VALUES ('2', 2, '2022-05-12', '2022-05-17', 97.84, 'RASCUNHO')");
//
//            st.execute("INSERT INTO ITEM_PEDIDO (id, id_pedido, id_produto, quantidade, preco_unitario, desconto, total_item) " +
//            "VALUES ('3', 2, 3, 3, 22.00, 0, 66.00)");
//            st.execute("INSERT INTO ITEM_PEDIDO (id, id_pedido, id_produto, quantidade, preco_unitario, desconto, total_item) " +
//            "VALUES ('4', 2, 1, 4, 19.90, 0.4, 31.84)");
//
//            st.execute("INSERT INTO PEDIDO (id, id_cliente, data_emissao, data_entrega, total_pedido, status) " +
//                    "VALUES ('3', 1, '2022-05-07', '2022-05-07', 97.84, 'RASCUNHO')");
//
//            st.execute("INSERT INTO ITEM_PEDIDO (id, id_pedido, id_produto, quantidade, preco_unitario, desconto, total_item) " +
//                    "VALUES ('5', 3, 1, 3, 22.00, 0, 66.00)");
//            st.execute("INSERT INTO ITEM_PEDIDO (id, id_pedido, id_produto, quantidade, preco_unitario, desconto, total_item) " +
//                    "VALUES ('6', 3, 2, 4, 19.90, 0.4, 31.84)");

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
