package setup;

import server.database.Database;
import org.apache.ibatis.jdbc.ScriptRunner;
import server.wiring.Wiring;

import static utils.readers.FileReader.getFileReader;

public class DatabaseSetup {

    //TODO fix this so that we can start running scripts on app start up again (new oracle scripts).
    public static boolean databaseSetupIsHealthy(Wiring wiring) {
        Database database = wiring.database();
        if (database.status().equals("FAIL")) {
            return false;
        } else {
            //databaseRunScripts(database);
            return true;
        }
    }

    private static void databaseRunScripts(Database database) {
        System.out.println("-------------------------");
        System.out.println("Running server.database scripts");
        System.out.println("-------------------------");

        ScriptRunner runner = new ScriptRunner(database.connection());

        runner.runScript(getFileReader("sql/schema-mysql.sql"));

        System.out.println("Database is up to date.");
        System.out.println("-------------------------");
    }

}


