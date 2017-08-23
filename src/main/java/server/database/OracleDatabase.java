package server.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleConnection;
import utils.properties.DatabaseProperties;
import server.jetty.servlets.model.probes.OracleProbe;
import server.jetty.servlets.model.probes.Probe;

import static java.lang.String.valueOf;
import static server.database.repositories.StaffColumn.*;

public class OracleDatabase implements Database {

    private OracleConnection connection;
    private DatabaseProperties properties;

    //TODO push all of this db logic up into wiring so it is pushed through the app rather than being isolated here.
    public OracleDatabase(DatabaseProperties properties) {
        this.connection = new OracleConnectionFactory(properties).create();
        this.properties = properties;

        postConnectionScripts();
    }

    private void postConnectionScripts() {
        try {
            createStatement().executeQuery("ALTER SESSION SET CURRENT_SCHEMA = app_owner");

            usingEdition(1);
            printResultsFromView();

            usingEdition(properties.databaseEdition());
            printResultsFromView();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO remove this from here - was just for testing new oracle edition implementation
    @Deprecated
    private void printResultsFromView() throws SQLException {
        ResultSet resultSet = createStatement().executeQuery("SELECT * FROM staff_view");

        while(resultSet.next()) {
            System.out.println("id: " + ID.getValueAsString(resultSet) +
                    " title: " + TITLE.getValueAsString(resultSet) +
                    " name: " + FIRSTNAME.getValueAsString(resultSet) +
                    " surname: " + SURNAME.getValueAsString(resultSet));
        }
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
            // return connection.isValid(utils.readers.properties.databaseTimeout()) ? "OK" : "FAIL";
        } catch (Exception e) {
            //Do nothing
        }
        return "FAIL";
    }

    private Statement createStatement() throws SQLException {
        return this.connection.createStatement(); //Default ResultSet => TYPE_FORWARD_ONLY (Read More)
    }

    private void usingEdition(int editionNum) throws SQLException {
        createStatement().executeQuery("ALTER SESSION SET EDITION = ED_" + editionNum);
        System.out.println("Set edition session to ED_" + editionNum);
    }
}
