package server.database;

import static utils.Properties.getDatabaseSettings;

public class DatabaseBuilder {

    public static MySQLDatabase build(String databaseName) {
        return new MySQLDatabase(getDatabaseSettings(), databaseName);
    }
}
