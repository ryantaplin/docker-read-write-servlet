package setup;

import org.eclipse.jetty.servlet.ServletContextHandler;
import server.jetty.JettyServer;
import server.jetty.handlers.ReadHandlerBuilder;
import server.jetty.handlers.WriteHandlerBuilder;
import server.wiring.Wiring;

import static utils.readers.EnvironmentVariableReader.getAppRole;
import static utils.readers.EnvironmentVariableReader.getEnvironment;

public class ServerSetup {

    public static void startServer(Wiring wiring) {
        JettyServer server = new JettyServer(wiring);
        server.withContext(getHandlerForApp(wiring));
        server.start();
        System.out.println(String.format("Server has successfully loaded under '%s' environment.", getEnvironment()));
    }

    private static ServletContextHandler getHandlerForApp(Wiring wiring) {
        switch (getAppRole()) {
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
