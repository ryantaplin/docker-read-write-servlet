package utils;

import database.DatabaseSettings;
import server.ServerSettings;

public class Settings {

    public static ServerSettings getServerSettings() {
        String environment = EnvironmentVariableReader.getEnvironment();
        return new ServerSettings(new PropertiesReader(environment));
    }

    public static DatabaseSettings getDatabaseSettings() {
        String environment = EnvironmentVariableReader.getEnvironment();
        return new DatabaseSettings(new PropertiesReader(environment));
    }
}
