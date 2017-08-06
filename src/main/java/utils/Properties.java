package utils;

import properties.DatabaseProperties;
import properties.ServerProperties;
import utils.readers.PropertiesReader;

import static utils.readers.EnvironmentVariableReader.*;

public class Properties {

    public static ServerProperties getServerSettings() {
        return new ServerProperties(new PropertiesReader(getEnvironment()));
    }

    public static DatabaseProperties getDatabaseSettings() {
        return new DatabaseProperties(new PropertiesReader(getEnvironment()));
    }
}
