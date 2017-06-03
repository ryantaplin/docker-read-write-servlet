import database.BasicDatabase;
import database.BasicDatabaseBuilder;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.eclipse.jetty.servlet.ServletContextHandler;
import server.BasicServer;
import server.handlers.ReadHandlerBuilder;
import server.handlers.WriteHandlerBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import static utils.EnvironmentVariableReader.getAppRole;
import static utils.EnvironmentVariableReader.getEnvironment;
import static utils.FileReader.getFileReader;
import static utils.Settings.getServerSettings;

public class App {

    public static void main(String[] args) {
        databaseChecksAndSetup();

        BasicServer server = new BasicServer(getServerSettings());
        server.withContext(getHandlerForApp());
        server.start();

        System.out.println(String.format("Server has successfully loaded under '%s' environment.", getEnvironment()));
    }

    private static void databaseChecksAndSetup() {
        System.out.println("Running database scripts");
        System.out.println("-------------------------");

        BasicDatabase database = BasicDatabaseBuilder.build();
        ScriptRunner runner = new ScriptRunner(database.connection);

        runner.runScript(getFileReader("sql/schema.sql"));
        System.out.println("Database is up to date.");
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
