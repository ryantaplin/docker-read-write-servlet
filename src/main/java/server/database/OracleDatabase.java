package server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleConnection;
import properties.DatabaseProperties;
import server.jetty.servlets.model.OracleProbe;
import server.jetty.servlets.model.Probe;

public class OracleDatabase implements Database {

    private OracleConnection connection;
    private DatabaseProperties properties;


    //TODO push all of this db logic up into wiring so it is pushed through the app rather than being isolated here.
    // i.e. Connection
    public OracleDatabase(DatabaseProperties properties) {
        this.connection = new OracleConnectionFactory(properties).create();
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

    public Probe probe() {
        return new OracleProbe(
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

    private Statement createStatement() throws SQLException {
        return this.connection.createStatement(); //Default ResultSet => TYPE_FORWARD_ONLY (Read More)
    }
}
