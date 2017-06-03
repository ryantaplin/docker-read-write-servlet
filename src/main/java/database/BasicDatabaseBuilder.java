package database;

import static utils.Settings.getDatabaseSettings;

public class BasicDatabaseBuilder {

    private static String databaseName = "";

    private BasicDatabaseBuilder(String name) {
        databaseName = name;
    }

    public static BasicDatabase build() {
        return new BasicDatabase(getDatabaseSettings(), databaseName);
    }

    public static BasicDatabaseBuilder as(String name) {
        return new BasicDatabaseBuilder(name);
    }
}
