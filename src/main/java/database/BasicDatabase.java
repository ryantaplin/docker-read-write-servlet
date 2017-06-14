package database;

import model.Probe;
import org.json.JSONObject;
import settings.DatabaseSettings;

import java.sql.*;

public class BasicDatabase {

    public Connection connection;
    private DatabaseSettings settings;
    private String databaseName;

    public BasicDatabase(DatabaseSettings settings, String databaseName) {
        String database = settings.databaseURL() + databaseName;
        try {
            this.connection = DriverManager.getConnection(database, settings.databaseUsername(), settings.databasePassword());
        } catch (SQLException e) {
            System.out.println("There was a problem connecting to the database: " + database);
        }
        this.databaseName = databaseName;
        this.settings = settings;
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

    public Probe probe() {
        return new Probe(String.format(
                "MySQL %s Database", databaseName),
                status(),
                String.format("[user=%s][url=%s]", settings.databaseUsername(), settings.databaseURL() + databaseName));
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
