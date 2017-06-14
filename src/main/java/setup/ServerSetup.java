package setup;

import org.eclipse.jetty.servlet.ServletContextHandler;
import server.BasicServer;
import server.handlers.ReadHandlerBuilder;
import server.handlers.WriteHandlerBuilder;

import static utils.EnvironmentVariableReader.getAppRole;
import static utils.EnvironmentVariableReader.getEnvironment;
import static utils.Settings.getServerSettings;

public class ServerSetup {

    public static void startServer() {
        BasicServer server = new BasicServer(getServerSettings());
        server.withContext(getHandlerForApp());
        server.start();
        System.out.println(String.format("Server has successfully loaded under '%s' environment.", getEnvironment()));
    }

    private static ServletContextHandler getHandlerForApp() {
        switch (getAppRole()) {
            case "write":
                System.out.println("Application has been assigned WRITE-role.");
                return new WriteHandlerBuilder().withAddServlet().build();
            case "read":
                System.out.println("Application has been assigned READ-role.");
                return new ReadHandlerBuilder().withReadServlet().build();
            default:
                throw new IllegalStateException("No role for application is defined.");
        }
    }
}
