package utils.properties;

import utils.readers.PropertiesReader;

public class DatabaseProperties extends Properties {

    public DatabaseProperties(PropertiesReader propertiesReader) {
        super(propertiesReader);
    }

    public int databaseTimeout() {
        return Integer.parseInt(propertiesReader.readProperty("database.timeout"));
    }

    public int databaseMaxRetryAttempts() {
        return Integer.parseInt(propertiesReader.readProperty("database.maxAttempts"));
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

    public int databaseEdition() {
        return Integer.parseInt(propertiesReader.readProperty("database.edition"));
    }

}
