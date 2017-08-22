package server.wiring;

import properties.DatabaseProperties;
import properties.ServerProperties;
import server.database.Database;
import server.database.DatabaseBuilder;
import server.database.OracleDatabase;
import server.database.OracleDatabaseBuilder;
import server.database.repositories.StaffRepository;
import server.jetty.servlets.AddServlet;
import server.jetty.servlets.ReadServlet;
import server.jetty.servlets.ReadyServlet;
import server.jetty.servlets.StatusServlet;
import server.jetty.servlets.model.Probe;
import server.jetty.servlets.model.Status;
import utils.readers.EnvironmentVariableReader;
import utils.readers.PropertiesReader;

import javax.servlet.http.HttpServlet;
import java.sql.SQLException;
import java.util.List;

public class WiringImpl implements Wiring {

    public ServerProperties serverProperties() {
        return new ServerProperties(getPropertiesReader());
    }

    public DatabaseProperties databaseProperties() {
        return new DatabaseProperties(getPropertiesReader());
    }

    public HttpServlet readyServlet() {
        return new ReadyServlet(this);
    }

    public HttpServlet statusServlet() {
        return new StatusServlet(this);
    }

    public HttpServlet readServlet() {
        return new ReadServlet(this);
    }

    public HttpServlet writeServlet() {
        return new AddServlet(this);
    }

    public Database database() {
        return new OracleDatabaseBuilder(this).build();
    }

    public EnvironmentVariableReader environmentVariableReader() {
        return new EnvironmentVariableReader(this);
    }

    public Status status(List<Probe> probes) {
        return new Status(probes, this);
    }

    public StaffRepository staffRepository() throws SQLException {
        return new StaffRepository(this);
    }

    private PropertiesReader getPropertiesReader() {
        return new PropertiesReader(environmentVariableReader().getEnvironment());
    }
}
