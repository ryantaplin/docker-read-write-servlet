package database;

import java.sql.*;

public class BasicDatabase {

    public Connection connection;
    private DatabaseSettings settings;

    public BasicDatabase(DatabaseSettings settings) {
        try {
            this.connection = DriverManager.getConnection(settings.databaseURL(), settings.databaseUsername(), settings.databasePassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.settings = settings;
        System.out.println(String.format("Accessing database '%s' with '%s'.", settings.databaseURL(), settings.databaseUsername()));
    }

    public ResultSet query(String sql) throws SQLException {
        return createStatement().executeQuery(sql);
    }

    public void update(String sql) throws SQLException {
        createStatement().executeUpdate(sql);
    }

    private Statement createStatement() throws SQLException {
        return this.connection.createStatement(); //Default ResultSet => TYPE_FORWARD_ONLY (Read More)
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String status() {
        try {
            return connection.isValid(settings.databaseTimeout()) ? "OK" : "FAIL";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "FAIL";
    }
}
