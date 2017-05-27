package database;

import utils.PropertiesReader;

public class DatabaseSettings {
    private PropertiesReader propertiesReader;

    public DatabaseSettings(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

//    public int databasePort() {
//        return Integer.parseInt(propertiesReader.readProperty("database.port"));
//    }

    public int databaseTimeout() {
        return Integer.parseInt(propertiesReader.readProperty("database.timeout"));
    }

    public String databaseURL() {
        return propertiesReader.readProperty("database.url");
    }

    public String databaseUsername() {
        return propertiesReader.readProperty("database.username");
    }

    public String databasePassword() {
        return propertiesReader.readProperty("database.password");
    }

}
