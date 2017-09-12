package server.wiring;

import org.eclipse.jetty.servlet.ServletContextHandler;
import server.jetty.handlers.ReadHandlerBuilder;
import server.jetty.handlers.WriteHandlerBuilder;
import utils.properties.DatabaseProperties;
import utils.properties.ServerProperties;
import server.database.Database;
import server.database.builder.OracleDatabaseFactory;
import server.database.repositories.StaffRepository;
import server.jetty.servlets.AddServlet;
import server.jetty.servlets.ReadServlet;
import server.jetty.servlets.ReadyServlet;
import server.jetty.servlets.StatusServlet;
import server.jetty.servlets.model.probes.Probe;
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
        return new ReadyServlet();
    }

    public HttpServlet statusServlet() {
        return new StatusServlet(this);
    }

    public ServletContextHandler readHandler() {
        return new ReadHandlerBuilder(this).withReadServlet().build();
    }

    public ServletContextHandler writeHandler() {
        return new WriteHandlerBuilder(this).withAddServlet().build();
    }

    public HttpServlet readServlet() {
        return new ReadServlet(this);
    }

    public HttpServlet writeServlet() {
        return new AddServlet(this);
    }

    public Database database() {
        return new OracleDatabaseFactory(this).build();
    }

    public EnvironmentVariableReader environmentVariableReader() {
        return new EnvironmentVariableReader();
    }

    public Status status(List<Probe> probes) {
        return new Status(probes, this);
    }

    public StaffRepository staffRepository() throws SQLException {
        //TODO insert in here the database as opposed to the wiring? Is wiring needed here?
        return new StaffRepository(this);
    }

    private PropertiesReader getPropertiesReader() {
        return new PropertiesReader(environmentVariableReader().getEnvironment());
    }
}
