package br.com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private String datasource = "jdbc:postgresql://localhost:5432/apitable?autoReconnect=true&useSSL=false";
    private String user = "postgres";
    private String password = "postgres";

    public Connection getConnection() {
        try {

            return DriverManager.getConnection(datasource , user, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
