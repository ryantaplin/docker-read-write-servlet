package setup;

import database.BasicDatabase;
import database.BasicDatabaseBuilder;
import org.apache.ibatis.jdbc.ScriptRunner;

import static utils.FileReader.getFileReader;

public class DatabaseSetup {

    public static boolean databaseSetupIsHealthy() {
        BasicDatabase database = BasicDatabaseBuilder.build("");
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

}


