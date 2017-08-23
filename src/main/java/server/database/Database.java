package server.database;

import server.jetty.servlets.model.probes.Probe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {

    //TODO ResultSet is tied to implementation of SQL/ MySql? Need to change to return generic thing (i.e Json/ Object)
    ResultSet query(String sql) throws SQLException;
    Probe probe();
    String status();
    Connection connection();

    void update(String sql) throws SQLException;

}
