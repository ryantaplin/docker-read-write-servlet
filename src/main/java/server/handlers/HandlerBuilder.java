package server.handlers;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.servlets.ReadyServlet;
import server.servlets.StatusServlet;

import static server.wiring.WriteEndpoints.READY_ENDPOINT;
import static server.wiring.WriteEndpoints.STATUS_ENDPOINT;

public abstract class HandlerBuilder {

    ServletContextHandler handler;

    public HandlerBuilder() {
        this.handler = new ServletContextHandler();
        this.withStatusServlet().withReadyServlet();
    }

    private HandlerBuilder withReadyServlet() {
        handler.addServlet(new ServletHolder(new ReadyServlet()), READY_ENDPOINT);
        return this;
    }

    private HandlerBuilder withStatusServlet() {
        handler.addServlet(new ServletHolder(new StatusServlet()), STATUS_ENDPOINT);
        return this;
    }

    public ServletContextHandler build() {
        return handler;
    }

}
