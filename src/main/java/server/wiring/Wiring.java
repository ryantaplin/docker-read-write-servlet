package server.wiring;

import utils.properties.DatabaseProperties;
import utils.properties.ServerProperties;
import server.database.Database;
import server.database.repositories.StaffRepository;
import server.jetty.servlets.model.probes.Probe;
import server.jetty.servlets.model.Status;
import utils.readers.EnvironmentVariableReader;

import javax.servlet.http.HttpServlet;
import java.sql.SQLException;
import java.util.List;

public interface Wiring {

    ServerProperties serverProperties();

    DatabaseProperties databaseProperties();

    HttpServlet readyServlet();

    HttpServlet statusServlet();

    HttpServlet readServlet();

    HttpServlet writeServlet();

    StaffRepository staffRepository() throws SQLException;

    Database database();

    EnvironmentVariableReader environmentVariableReader();

    Status status(List<Probe> probes);
}
