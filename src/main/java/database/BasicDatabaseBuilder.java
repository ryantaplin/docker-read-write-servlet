package database;

import static utils.Settings.getDatabaseSettings;

public class BasicDatabaseBuilder {

    public static BasicDatabase build() {
        return new BasicDatabase(getDatabaseSettings());
    }
}
