package server.database;

import server.jetty.servlets.model.Probe;
import properties.DatabaseProperties;

import oracle.jdbc.pool.OracleDataSource;
import java.sql.*;

public class OracleDatabase implements Database {

    private Connection connection;
    private DatabaseProperties properties;
    private String databaseName;

    OracleDatabase(DatabaseProperties properties, String databaseName) {
        try {
            OracleDataSource ds = new OracleDataSource();

            ds.setURL(properties.databaseURL());
            ds.setUser(properties.databaseUsername());
            ds.setPassword(properties.databasePassword());

            this.connection = ds.getConnection();

//            this.connection = DriverManager.getConnection(properties.databaseURL(), properties.databaseUsername(), properties.databasePassword());
        } catch (SQLException e) {
            System.out.println("There was a problem connecting to the server.database: " + properties.databaseURL());
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

    public Connection connection() {
        return connection;
    }

    private Statement createStatement() throws SQLException {
        return this.connection.createStatement(); //Default ResultSet => TYPE_FORWARD_ONLY (Read More)
    }

    public Probe probe() {
        return new Probe(String.format(
                "Oracle %s Database", databaseName),
                status(),
                String.format("[user=%s][url=%s]", properties.databaseUsername(), properties.databaseURL() + databaseName));
    }

    public String status() {
        try {
            return connection.isValid(properties.databaseTimeout()) ? "OK" : "FAIL";
        } catch (SQLException e) {
            //Do nothing
        }
        return "FAIL";
    }
}
