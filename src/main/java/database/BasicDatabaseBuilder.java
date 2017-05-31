package database;

import utils.EnvironmentVariableReader;
import utils.PropertiesReader;

public class BasicDatabaseBuilder {

    public static BasicDatabase build() {
        String environment = EnvironmentVariableReader.getEnvironment();
        DatabaseSettings settings = new DatabaseSettings(new PropertiesReader(environment));
        return new BasicDatabase(settings);
    }
}
