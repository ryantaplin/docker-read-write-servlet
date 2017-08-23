package server.jetty.handlers;

import static server.wiring.endpoints.ReadEndpoints.*;

public class ReadHandlerBuilder extends HandlerBuilder {

    public ReadHandlerBuilder withReadServlet() {
        addServlet(wiring.readServlet(), READ_ENDPOINT);
        return this;
    }
}
