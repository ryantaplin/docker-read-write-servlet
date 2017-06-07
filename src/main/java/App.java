import database.BasicDatabase;
import database.BasicDatabaseBuilder;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.eclipse.jetty.servlet.ServletContextHandler;
import server.BasicServer;
import server.handlers.ReadHandlerBuilder;
import server.handlers.WriteHandlerBuilder;

import java.util.concurrent.BrokenBarrierException;

import static utils.EnvironmentVariableReader.getAppRole;
import static utils.EnvironmentVariableReader.getEnvironment;
import static utils.FileReader.getFileReader;
import static utils.Settings.getServerSettings;

public class App {

    public static void main(String[] args) {
        System.out.printf("Application starting.%n%n");

        startApplication(1);
    }

    private static void startApplication(int attempts) {
        if (attempts >= 10) //extract n of attempts out to properties.
            throw new RuntimeException("Database did not connect after " + attempts + " attempts.");

        if (!databaseSetupIsHealthy()) {
            System.out.println("Attempt " + attempts + " at starting the application.");
            retryApplicationStartUp(attempts + 1);
        } else {
            startServer();
        }
    }

    private static void retryApplicationStartUp(int attempt) {
        int retryTime = 30; // extract out to properties.
        System.out.println("Database status failed... Retrying in " + retryTime + " seconds.");
        System.out.println("");

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        startApplication(attempt);
                    }
                },
                retryTime * 100
        );
    }

    private static void startServer() {
        BasicServer server = new BasicServer(getServerSettings());
        server.withContext(getHandlerForApp());
        server.start();
        System.out.println(String.format("Server has successfully loaded under '%s' environment.", getEnvironment()));
    }

    private static boolean databaseSetupIsHealthy() {
        BasicDatabase database =  BasicDatabaseBuilder.build("");
        if (database.status().equals("FAIL")) {
            return false;
        } else {
            databaseRunScripts(database);
            return true;
        }
    }

    private static void databaseRunScripts(BasicDatabase database) {
        System.out.println("-------------------------");
        System.out.println("Running database scripts");
        System.out.println("-------------------------");

        ScriptRunner runner = new ScriptRunner(database.connection);

        runner.runScript(getFileReader("sql/schema.sql"));
        System.out.println("Database is up to date.");
        System.out.println("-------------------------");
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
