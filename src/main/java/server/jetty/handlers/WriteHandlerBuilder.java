package server.jetty.handlers;


import server.jetty.servlets.AddServlet;
import server.wiring.Wiring;

import static server.wiring.endpoints.WriteEndpoints.*;

public class WriteHandlerBuilder extends HandlerBuilder {

    public WriteHandlerBuilder(Wiring wiring) {
        super(wiring);
    }

    public WriteHandlerBuilder withAddServlet() {
        addServlet(new AddServlet(wiring.databaseProperties(), wiring.staffRepository()), WRITE_ENDPOINT);
        return this;
    }
}
