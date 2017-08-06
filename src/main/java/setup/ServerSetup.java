package setup;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import server.jetty.JettyServer;
import server.jetty.handlers.ReadHandlerBuilder;
import server.jetty.handlers.WriteHandlerBuilder;
import server.wiring.Wiring;

public class ServerSetup {

    private static JettyServer server;

    public static void startServer(Wiring wiring) {
        server = new JettyServer(wiring);
        server.withContext(getHandlerForApp(wiring));
        server.start();
        System.out.println(String.format("Server has successfully loaded under '%s' environment.", wiring.environmentVariableReader().getEnvironment()));
    }

    public static void stopServer() {
        server.stop();
    }

    private static ServletContextHandler getHandlerForApp(Wiring wiring) {
        switch (wiring.environmentVariableReader().getAppRole()) {
            case "write":
                System.out.println("Application has been assigned WRITE-role.");
                return new WriteHandlerBuilder(wiring).withAddServlet().build();
            case "read":
                System.out.println("Application has been assigned READ-role.");
                return new ReadHandlerBuilder(wiring).withReadServlet().build();
            default:
                throw new IllegalStateException("No role for application is defined.");
        }
    }
}
