package server.jetty.servlets;

import server.database.Database;
import server.jetty.servlets.model.Status;
import server.jetty.servlets.model.probes.Probe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class StatusServlet extends HttpServlet {

    private Database database;
    private String environment;

    public StatusServlet(Database database, String environment) {
        this.database = database;
        this.environment = environment;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        List<Probe> probes = asList(database.probe());
        new Status(probes, environment).json().write(response.getWriter());
    }

}
