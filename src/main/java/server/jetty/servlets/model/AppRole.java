package server.jetty.servlets.model;

import org.eclipse.jetty.servlet.ServletContextHandler;
import server.jetty.handlers.HandlerBuilder;
import server.jetty.handlers.ReadHandlerBuilder;
import server.jetty.handlers.WriteHandlerBuilder;
import server.wiring.Wiring;
import server.wiring.WiringImpl;

/* Wasn't working to reverted it for now. */

@Deprecated
public enum AppRole {
    READ(new ReadHandlerBuilder(new WiringImpl()).withReadServlet()),
    WRITE(new WriteHandlerBuilder(new WiringImpl()).withAddServlet());

    /** Enum impl */
    private HandlerBuilder handlerBuilder;

    AppRole(HandlerBuilder handlerBuilder) {
        this.handlerBuilder = handlerBuilder;
    }

//    public ServletContextHandler getHandler(Wiring wiring) {
//        System.out.println("Application has been assigned" +  String.valueOf(this) + "-role.");
//        return handlerBuilder.build();
//    }
}
