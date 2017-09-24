package server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.properties.DatabaseProperties;
import server.jetty.servlets.model.probes.OracleProbe;
import server.jetty.servlets.model.probes.Probe;

import static java.lang.String.valueOf;

public class OracleDatabase implements Database {

    private OracleConnection connection;
    private DatabaseProperties properties;

    public OracleDatabase(DatabaseProperties properties) {
        this.connection = new OracleConnectionFactory(properties).create();
        this.properties = properties;

        postConnectionScripts();
    }

    private void postConnectionScripts() {
        try {
            createStatement().executeQuery("ALTER SESSION SET CURRENT_SCHEMA = app_owner");
            usingEdition(properties.databaseEdition());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JSONArray query(String sql) throws SQLException {
        return convertResultsToJSON(createStatement().executeQuery(sql));
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

    public String status() {
        try {
             //return connection != null ? "OK" : "FAIL";
             return connection.isValid(properties.databaseTimeout()) ? "OK" : "FAIL";
        } catch (Exception e) {
            //Do nothing
        }
        return "FAIL";
    }

    private Statement createStatement() throws SQLException {
        return this.connection.createStatement();
    }

    private void usingEdition(int editionNum) throws SQLException {
        createStatement().executeQuery("ALTER SESSION SET EDITION = ED_" + editionNum);
        System.out.println("Set edition session to ED_" + editionNum);
    }

    private JSONArray convertResultsToJSON(ResultSet result) throws SQLException {
        JSONArray array = new JSONArray();
        while (result.next()) {
            int colCount = result.getMetaData().getColumnCount();
            for (int i = 0; i < colCount; i++) {
                JSONObject obj = new JSONObject();
                obj.put(result.getMetaData().getColumnLabel(i + 1).toLowerCase(), result.getObject(i + 1));
                array.put(obj);
            }
        }
        return array;
    }
}
