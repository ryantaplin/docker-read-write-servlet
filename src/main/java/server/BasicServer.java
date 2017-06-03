package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import settings.ServerSettings;

import java.util.Arrays;

public class BasicServer {
    private final Server server;

    public BasicServer(ServerSettings settings) {
        this.server = new Server(settings.serverPort());
    }

    public void withContext(ServletContextHandler servletHandler) {
        server.setHandler(servletHandler);
    }

    public void start() {
        try {
            server.start();
        } catch (Exception e) {
            System.out.println(String.format("Server could not start: %n%s", Arrays.toString(e.getStackTrace())));
        }
    }

    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            System.out.println(String.format("Server could not stop: %n%s%n", Arrays.toString(e.getStackTrace())));
        }
    }
}
