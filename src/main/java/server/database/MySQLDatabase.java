package server.database;

import server.jetty.servlets.model.Probe;
import properties.DatabaseProperties;

import java.sql.*;

public class MySQLDatabase implements Database {

    public Connection connection;
    private DatabaseProperties properties;
    private String databaseName;

    public MySQLDatabase(DatabaseProperties properties, String databaseName) {
        String database = properties.databaseURL() + databaseName;
        try {
            this.connection = DriverManager.getConnection(database, properties.databaseUsername(), properties.databasePassword());
        } catch (SQLException e) {
            System.out.println("There was a problem connecting to the server.database: " + database);
        }
        this.databaseName = databaseName;
        this.properties = properties;
    }

    public ResultSet query(String sql) throws SQLException {
        return createStatement().executeQuery(sql);
    }

    public void update(String sql) throws SQLException {
        createStatement().executeUpdate(sql);
    }

    public Probe probe() {
        return new Probe(String.format(
                "MySQL %s Database", databaseName),
                status(),
                String.format("[user=%s][url=%s]", properties.databaseUsername(), properties.databaseURL() + databaseName));
    }

    public String status() {
        try {
            return connection.isValid(properties.databaseTimeout()) ? "OK" : "FAIL";
        } catch (Exception e) {
            //Do nothing
        }
        return "FAIL";
    }

    private Statement createStatement() throws SQLException {
        return this.connection.createStatement(); //Default ResultSet => TYPE_FORWARD_ONLY (Read More)
    }
}
