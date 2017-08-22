package server.jetty.servlets;

import server.jetty.servlets.model.Probe;
import server.wiring.Wiring;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class StatusServlet extends HttpServlet {

    private Wiring wiring;

    public StatusServlet(Wiring wiring) {
        this.wiring = wiring;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        List<Probe> probes = asList(wiring.database().probe());
        wiring.status(probes).json().write(response.getWriter());
    }
}
