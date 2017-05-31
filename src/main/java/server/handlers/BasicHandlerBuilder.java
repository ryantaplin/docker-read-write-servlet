package server.handlers;

import server.servlets.AddServlet;
import server.servlets.MainServlet;
import server.servlets.ReadyServlet;
import server.servlets.StatusServlet;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import static server.wiring.Endpoints.*;

public class BasicHandlerBuilder {

    private ServletContextHandler handler;

    public BasicHandlerBuilder() {
        this.handler = new ServletContextHandler();
    }

    public BasicHandlerBuilder withMainServlet() {
        handler.addServlet(new ServletHolder(new MainServlet()), DEFAULT_ENDPOINT);
        return this;
    }

    public BasicHandlerBuilder withReadyServlet() {
        handler.addServlet(new ServletHolder(new ReadyServlet()), READY_ENDPOINT);
        return this;
    }

    public BasicHandlerBuilder withStatusServlet() {
        handler.addServlet(new ServletHolder(new StatusServlet()), STATUS_ENDPOINT);
        return this;
    }

    public BasicHandlerBuilder withAddServlet() {
        handler.addServlet(new ServletHolder(new AddServlet()), ADD_ENDPOINT);
        return this;
    }

    public ServletContextHandler build() {
        return handler;
    }
}
