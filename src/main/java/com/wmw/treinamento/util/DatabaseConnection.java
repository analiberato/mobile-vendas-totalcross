package com.wmw.treinamento.util;

import totalcross.db.sqlite.SQLiteUtil;
import totalcross.sql.Connection;
import totalcross.sql.Statement;
import totalcross.sys.Settings;

import java.sql.SQLException;

public class DatabaseConnection {
    public static SQLiteUtil sqliteUtil;

    public static Connection getConnection() throws SQLException {
        sqliteUtil = new SQLiteUtil(Settings.appPath, "vendas.db");
        Statement st = sqliteUtil.con().createStatement();
        return sqliteUtil.con();
    }

}
