package be.intecbrussel.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConfig {

    public static Connection getConnection() {
        String user = "Gabriel Alves";
        String passw = "14789632";
        String url = "localhost";
        String port = "3306";
        String database = "accountapp_db";

        String connectionString = String.format("jdbc:mysql://%s:%s/%s", url, port, database);

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", user);
        connectionProperties.put("password", passw);

        Connection connection;
        try {
            connection = DriverManager.getConnection(connectionString, connectionProperties);
        } catch (SQLException e) {
            System.err.println("ERROR: COULD NOT CONNECT TO DATABASE.");
            throw new RuntimeException(e);
        }

        return connection;
    }
}
