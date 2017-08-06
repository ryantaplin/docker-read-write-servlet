package server.wiring;

import properties.DatabaseProperties;
import properties.ServerProperties;
import server.database.Database;
import server.database.DatabaseBuilder;
import server.database.repositories.StaffRepository;
import server.jetty.servlets.AddServlet;
import server.jetty.servlets.ReadServlet;
import server.jetty.servlets.ReadyServlet;
import server.jetty.servlets.StatusServlet;
import utils.readers.EnvironmentVariableReader;
import utils.readers.PropertiesReader;

import javax.servlet.http.HttpServlet;
import java.sql.SQLException;

public class WiringImpl implements Wiring {

    private static final String DATABASE_NAME = "sky";

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

    public StaffRepository staffRepository() throws SQLException {
        return new StaffRepository(this);
    }

    public Database database() {
        return DatabaseBuilder.build(DATABASE_NAME);
    }

    private PropertiesReader getPropertiesReader() {
        return new PropertiesReader(EnvironmentVariableReader.getEnvironment());
    }
}
