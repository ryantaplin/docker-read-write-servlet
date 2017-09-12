package server.jetty.handlers;

import server.jetty.servlets.ReadServlet;
import server.wiring.Wiring;

import static server.wiring.endpoints.ReadEndpoints.*;

public class ReadHandlerBuilder extends HandlerBuilder {

    public ReadHandlerBuilder(Wiring wiring) {
        super(wiring);
    }

    public ReadHandlerBuilder withReadServlet() {
        addServlet(new ReadServlet(wiring.staffRepository()), READ_ENDPOINT);
        return this;
    }
}
