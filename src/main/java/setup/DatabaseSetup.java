package setup;

import server.database.MySQLDatabase;
import server.database.DatabaseBuilder;
import org.apache.ibatis.jdbc.ScriptRunner;

import static utils.readers.FileReader.getFileReader;

public class DatabaseSetup {

    public static boolean databaseSetupIsHealthy() {
        MySQLDatabase database = DatabaseBuilder.build("");
        if (database.status().equals("FAIL")) {
            return false;
        } else {
            databaseRunScripts(database);
            return true;
        }
    }

    private static void databaseRunScripts(MySQLDatabase database) {
        System.out.println("-------------------------");
        System.out.println("Running server.database scripts");
        System.out.println("-------------------------");

        ScriptRunner runner = new ScriptRunner(database.connection);

        runner.runScript(getFileReader("sql/schema-mysql.sql"));

        System.out.println("Database is up to date.");
        System.out.println("-------------------------");
    }

}


