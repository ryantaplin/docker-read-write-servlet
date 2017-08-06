package server.jetty.servlets;

import server.database.DatabaseBuilder;
import server.jetty.servlets.model.Probe;
import server.jetty.servlets.model.Status;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class StatusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        List<Probe> probes = asList(DatabaseBuilder.build("sky").probe());

        new Status(probes).json().write(response.getWriter());
    }
}
