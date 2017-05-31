package settings;

import utils.PropertiesReader;

public class DatabaseSettings extends Settings {

    public DatabaseSettings(PropertiesReader propertiesReader) {
        super(propertiesReader);
    }

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
