package server.jetty.handlers;

import server.wiring.Wiring;

import static server.wiring.endpoints.WriteEndpoints.*;

public class WriteHandlerBuilder extends HandlerBuilder {

    public WriteHandlerBuilder(Wiring wiring) {
        super(wiring);
    }

    public WriteHandlerBuilder withAddServlet() {
        addServlet(wiring.writeServlet(), WRITE_ENDPOINT);
        return this;
    }
}
