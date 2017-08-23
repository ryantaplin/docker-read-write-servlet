package setup;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import server.jetty.JettyServer;
import server.jetty.handlers.ReadHandlerBuilder;
import server.jetty.handlers.WriteHandlerBuilder;
import server.jetty.servlets.model.AppRole;
import server.wiring.Wiring;
import utils.readers.EnvironmentVariableReader;

import static server.jetty.servlets.model.AppRole.*;

public class ServerSetup {


    //TODO remove staticness here.
    private static JettyServer server;
    private static Wiring wiring;
    private static EnvironmentVariableReader environmentVariableReader;

    public static void startServer(Wiring wiring) {
        ServerSetup.wiring = wiring;
        ServerSetup.environmentVariableReader = wiring.environmentVariableReader();
        runNewServer(wiring);
    }

    private static void runNewServer(Wiring wiring) {
        server = new JettyServer(wiring);
        server.withContext(getHandlerForApp());
        server.start();

        System.out.println("Server has successfully loaded under " + environmentVariableReader.getEnvironment() + " environment.");
    }

    public static void stopServer() {
        server.stop();
    }

    private static ServletContextHandler getHandlerForApp() {
        return wiring.environmentVariableReader().getAppRole().getHandler(wiring);
    }
}
