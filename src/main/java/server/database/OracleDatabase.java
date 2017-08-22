package server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.OracleConnection;
import properties.DatabaseProperties;
import server.jetty.servlets.model.Probe;

public class OracleDatabase implements Database {

    private OracleConnection connection;
    private DatabaseProperties properties;

    OracleDatabase(DatabaseProperties properties) {
        try {
            OracleDataSource ds = new OracleDataSource();

            ds.setURL(properties.databaseURL());
            ds.setUser(properties.databaseUsername());
            ds.setPassword(properties.databasePassword());

            this.connection = (OracleConnection) ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("There was a problem connecting to the server.database: " + properties.databaseURL());
        }
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
        return new Probe(
                "Oracle Database",
                status(),
                String.format("[user=%s][url=%s]", properties.databaseUsername(), properties.databaseURL()));
    }


    // TODO fix this check some how. No longer supporting .isValid atm (something to do with JDBC vs JDK version?)
    public String status() {
        try {
            return connection != null ? "OK" : "FAIL";
            // return connection.isValid(properties.databaseTimeout()) ? "OK" : "FAIL";
        } catch (Exception e) {
            //Do nothing
        }
        return "FAIL";
    }
}
