package setup;

import org.eclipse.jetty.servlet.ServletContextHandler;
import server.jetty.JettyServer;
import server.jetty.handlers.ReadHandlerBuilder;
import server.jetty.handlers.WriteHandlerBuilder;
import server.wiring.Wiring;

public class ServerWrapper {

    private JettyServer server;
    private Wiring wiring;

    public ServerWrapper(Wiring wiring) {
        this.wiring = wiring;
        createNewServer(wiring);
    }

    private void createNewServer(Wiring wiring) {
        server = new JettyServer(wiring);
        server.withContext(getHandlerForApp());

        System.out.println("Server has successfully loaded under \'" + wiring.environment() + "\' environment.");
    }

    public void startServer() { server.start(); }

    public void stopServer() {
        server.stop();
    }

    private ServletContextHandler getHandlerForApp() {
        switch (wiring.appRole()) {
            case "READ":
                return new ReadHandlerBuilder(wiring).withReadServlet().build();
            case "WRITE":
                return new WriteHandlerBuilder(wiring).withAddServlet().build();
            default:
                throw new IllegalStateException("No valid role for application.");
        }
    }
}
