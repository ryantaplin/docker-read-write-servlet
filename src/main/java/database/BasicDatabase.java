package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BasicDatabase {

    public Connection connection;
    private DatabaseSettings settings;

    public BasicDatabase(DatabaseSettings settings) throws SQLException {
        this.connection = DriverManager.getConnection(settings.databaseURL(), settings.databaseUsername(), settings.databasePassword());
        this.settings = settings;

        System.out.println(String.format("Accessing database '%s' with '%s'.", settings.databaseURL(), settings.databaseUsername()));
    }

    public void close() throws SQLException {
        connection.close();
    }

    public String status() throws SQLException {
        return connection.isValid(settings.databaseTimeout()) ? "OK" : "FAIL";
    }
}
