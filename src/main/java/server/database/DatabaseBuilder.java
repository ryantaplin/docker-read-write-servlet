package server.database;

import static utils.Properties.getDatabaseProperties;

public class DatabaseBuilder {

    public static MySQLDatabase build(String databaseName) {
        return new MySQLDatabase(getDatabaseProperties(), databaseName);
    }
}
