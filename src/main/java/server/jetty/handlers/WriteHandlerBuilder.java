package server.jetty.handlers;


import static server.wiring.endpoints.WriteEndpoints.*;

public class WriteHandlerBuilder extends HandlerBuilder {

    public WriteHandlerBuilder withAddServlet() {
        addServlet(wiring.writeServlet(), WRITE_ENDPOINT);
        return this;
    }
}
