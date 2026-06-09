package org.example.dao;

import java.sql.*;

public class ConnectionDAO {
    Connection connection;

    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    String database = "clinica";
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/" + database;

    public Connection connectionDb()
    {
        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado ao banco com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o Banco de Dados: " + e.getMessage());
        }
        return connection;
    }
}
