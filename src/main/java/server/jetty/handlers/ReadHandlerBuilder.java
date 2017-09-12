package server.jetty.handlers;

import server.wiring.Wiring;

import static server.wiring.endpoints.ReadEndpoints.*;

public class ReadHandlerBuilder extends HandlerBuilder {

    public ReadHandlerBuilder(Wiring wiring) {
        super(wiring);
    }

    public ReadHandlerBuilder withReadServlet() {
        addServlet(wiring.readServlet(), READ_ENDPOINT);
        return this;
    }
}
