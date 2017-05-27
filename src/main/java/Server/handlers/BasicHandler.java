package Server.handlers;

import Server.servlets.MainServlet;
import Server.servlets.ReadyServlet;
import Server.servlets.StatusServlet;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import static Server.wiring.Endpoints.*;

public class BasicHandler {

    public static ServletContextHandler servletHandler() {
        ServletContextHandler servletHandler = new ServletContextHandler();

        servletHandler.addServlet(new ServletHolder(new MainServlet()), DEFAULT_ENDPOINT);

        servletHandler.addServlet(new ServletHolder(new ReadyServlet()), READY_ENDPOINT);
        servletHandler.addServlet(new ServletHolder(new StatusServlet()), STATUS_ENDPOINT);

        return servletHandler;
    }
}
