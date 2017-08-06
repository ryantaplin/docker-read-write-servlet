package server.wiring;

import properties.DatabaseProperties;
import properties.ServerProperties;
import server.database.Database;
import server.database.repositories.StaffRepository;

import javax.servlet.http.HttpServlet;
import java.sql.SQLException;

public interface Wiring {

    ServerProperties serverProperties();

    DatabaseProperties databaseProperties();

    HttpServlet readyServlet();

    HttpServlet statusServlet();

    HttpServlet readServlet();

    HttpServlet writeServlet();

    StaffRepository staffRepository() throws SQLException;

    Database database();
}
