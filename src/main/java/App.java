import server.BasicServer;
import server.handlers.BasicHandlerBuilder;

import static utils.EnvironmentVariableReader.getEnvironment;
import static utils.Settings.getServerSettings;

public class App {

    public static void main(String[] args) throws Exception {
        BasicServer server = new BasicServer(getServerSettings());
        server.withContext(
                new BasicHandlerBuilder()
                .withMainServlet()
                .withReadyServlet()
                .withStatusServlet()
                .withAddServlet().build());
        server.start();
        System.out.println(String.format("Server started in '%s' environment.", getEnvironment()));
    }
}
