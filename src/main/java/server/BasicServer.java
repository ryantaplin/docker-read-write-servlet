package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class BasicServer {
    private final Server server;

    public BasicServer(ServerSettings settings) {
        this.server = new Server(settings.serverPort()); //extract port into settings
    }

    public void withContext(ServletContextHandler servletHandler) {
        server.setHandler(servletHandler);
    }

    public void start() throws Exception {
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }
}
