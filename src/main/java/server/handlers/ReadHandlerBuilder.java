package server.handlers;

import org.eclipse.jetty.servlet.ServletHolder;
import server.servlets.ReadServlet;

import static server.wiring.ReadEndpoints.*;

public class ReadHandlerBuilder extends HandlerBuilder {

    public ReadHandlerBuilder() {
        super();
    }

    public ReadHandlerBuilder withReadServlet() {
        handler.addServlet(new ServletHolder(new ReadServlet()), READ_ENDPOINT);
        return this;
    }
}
