package utils;

import settings.DatabaseSettings;
import settings.ServerSettings;

import static utils.EnvironmentVariableReader.*;

public class Settings {

    public static ServerSettings getServerSettings() {
        return new ServerSettings(new PropertiesReader(getEnvironment()));
    }

    public static DatabaseSettings getDatabaseSettings() {
        return new DatabaseSettings(new PropertiesReader(getEnvironment()));
    }
}
