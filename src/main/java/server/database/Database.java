package server.database;

import org.json.JSONArray;
import server.jetty.servlets.model.probes.Probe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {

    JSONArray query(String sql) throws SQLException;
    Probe probe();
    String status();
    Connection connection();

    void update(String sql) throws SQLException;

}
