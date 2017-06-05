package database;

import static utils.Settings.getDatabaseSettings;

public class BasicDatabaseBuilder {

    public static BasicDatabase build(String databaseName) {
        return new BasicDatabase(getDatabaseSettings(), databaseName);
    }
}
