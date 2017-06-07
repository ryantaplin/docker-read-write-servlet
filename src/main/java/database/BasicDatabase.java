package database;

import settings.DatabaseSettings;

import java.sql.*;

public class BasicDatabase {

    public Connection connection;
    private DatabaseSettings settings;

    public BasicDatabase(DatabaseSettings settings, String databaseName) {
        String database = settings.databaseURL() + databaseName;
        try {
            this.connection = DriverManager.getConnection(database, settings.databaseUsername(), settings.databasePassword());
        } catch (SQLException e) {
            System.out.println("There was a problem connecting to the database: " + database);
        }
        this.settings = settings;
//        System.out.println(String.format("Accessing database '%s' as '%s'.", database, settings.databaseUsername()));
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

    public String status() {
        try {
            return connection.isValid(settings.databaseTimeout()) ? "OK" : "FAIL";
        } catch (Exception e) {
            //Do nothing
        }
        return "FAIL";
    }
}
