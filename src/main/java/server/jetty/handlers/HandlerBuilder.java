package server.jetty.handlers;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.jetty.servlets.ReadyServlet;
import server.jetty.servlets.StatusServlet;
import server.wiring.Wiring;

import javax.servlet.http.HttpServlet;

import static server.wiring.endpoints.Endpoints.STATUS_ENDPOINT;
import static server.wiring.endpoints.WriteEndpoints.READY_ENDPOINT;

public abstract class HandlerBuilder {

    public Wiring wiring;
    private ServletContextHandler handler;

    HandlerBuilder(Wiring wiring) {
        this.wiring = wiring;
        this.handler = new ServletContextHandler();
        this.withStatusServlet().withReadyServlet();
    }

    public ServletContextHandler build() {
        return handler;
    }

    void addServlet(HttpServlet httpServlet, String endpoint) {
        handler.addServlet(new ServletHolder(httpServlet), endpoint);
    }

    private HandlerBuilder withReadyServlet() {
        addServlet(new ReadyServlet(), READY_ENDPOINT);
        return this;
    }

    private HandlerBuilder withStatusServlet() {
        addServlet(new StatusServlet(wiring.database(), wiring.environment()), STATUS_ENDPOINT);
        return this;
    }
}
