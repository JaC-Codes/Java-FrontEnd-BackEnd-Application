package net.jack.javawebapp.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/registration", "postgres", "root");

    public Database() throws SQLException {
    }

    public void connect() {

        try {

            Class.forName("org.postgresql.Driver");

            if (connection != null) {
                System.out.println("Connected SUCCESSFUL");
            } else {
                System.out.println("Connection FAILED.");
            }

        } catch (
                Exception e) {

            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

