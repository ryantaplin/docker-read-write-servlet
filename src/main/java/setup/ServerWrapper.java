package setup;

import org.eclipse.jetty.servlet.ServletContextHandler;
import server.jetty.JettyServer;
import server.wiring.Wiring;
import utils.readers.EnvironmentVariableReader;

public class ServerWrapper {

    private JettyServer server;
    private Wiring wiring;
    private EnvironmentVariableReader environmentVariableReader;

    public ServerWrapper(Wiring wiring) {
        this.wiring = wiring;
        this.environmentVariableReader = wiring.environmentVariableReader();
        createNewServer(wiring);
    }

    private void createNewServer(Wiring wiring) {
        server = new JettyServer(wiring);
        server.withContext(getHandlerForApp());

        System.out.println("Server has successfully loaded under " + environmentVariableReader.getEnvironment() + " environment.");
    }

    public void startServer() { server.start(); }

    public void stopServer() {
        server.stop();
    }

    private ServletContextHandler getHandlerForApp() {
        String appRole = wiring.environmentVariableReader().getAppRole();

        switch (appRole) {
            case "READ":
                return wiring.readHandler();
            case "WRITE":
                return wiring.writeHandler();
            default:
                throw new IllegalStateException("No valid role for application.");
        }
    }
}
