package server.jetty.handlers;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.wiring.Wiring;

import javax.servlet.http.HttpServlet;

import static server.wiring.endpoints.Endpoints.STATUS_ENDPOINT;
import static server.wiring.endpoints.WriteEndpoints.READY_ENDPOINT;

public abstract class HandlerBuilder {

    public Wiring wiring;
    private ServletContextHandler handler;

    HandlerBuilder() {
        this.handler = new ServletContextHandler();
        this.withStatusServlet().withReadyServlet();
    }

    public void addServlet(HttpServlet httpServlet, String endpoint) {
        handler.addServlet(new ServletHolder(httpServlet), endpoint);
    }

    public void withWiring(Wiring wiring) {
        this.wiring = wiring;
    }

    public ServletContextHandler build() {
        return handler;
    }

    private HandlerBuilder withReadyServlet() {
        addServlet(wiring.readyServlet(), READY_ENDPOINT);
        return this;
    }

    private HandlerBuilder withStatusServlet() {
        addServlet(wiring.statusServlet(), STATUS_ENDPOINT);
        return this;
    }
}
