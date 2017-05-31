package server.handlers;

import org.eclipse.jetty.servlet.ServletHolder;
import server.servlets.AddServlet;

import static server.wiring.WriteEndpoints.*;

public class WriteHandlerBuilder extends HandlerBuilder {

    public WriteHandlerBuilder() {
        super();
    }

    public WriteHandlerBuilder withAddServlet() {
        handler.addServlet(new ServletHolder(new AddServlet()), WRITE_ENDPOINT);
        return this;
    }
}
