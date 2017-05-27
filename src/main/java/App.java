import Utils.Properties.EnvironmentVariableReader;
import Utils.Properties.PropertiesReader;
import Utils.Properties.ServerSettings;
import Utils.Properties.Settings;
import Server.BasicServer;
import Server.handlers.BasicHandler;

public class App {

    public static void main(String[] args) throws Exception {
        String environment = EnvironmentVariableReader.getEnvironment();
        System.out.println(String.format("Starting server with '%s' environment.", environment));

        ServerSettings settings = new Settings(new PropertiesReader(environment));

        BasicServer server = new BasicServer(settings);
        server.withContext(BasicHandler.servletHandler());

        server.start();
    }
}
