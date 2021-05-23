package com.company.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//singleton object for creating connection
public class DataBase {
    String url = "jdbc:postgresql://localhost/MySocialNetwork?user=postgres&password=valeria";
    private static DataBase myDataBase = null;
    private Connection con = null;

    private DataBase() {
        try {
            this.con = DriverManager.getConnection(
                    url);

        } catch (SQLException e) {
            System.err.println("Cannot connect to DB: " + e);
        }
    }

    public static DataBase getInstance() {
        if (myDataBase == null)
            myDataBase = new DataBase();

        return myDataBase;
    }

    public Connection getCon() {
        return con;
    }
}
