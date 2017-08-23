package utils.properties;

import utils.readers.PropertiesReader;

public class ServerProperties extends Properties {

    public ServerProperties(PropertiesReader propertiesReader) {
        super(propertiesReader);
    }

    public int serverPort() {
        return Integer.parseInt(propertiesReader.readProperty("server.port"));
    }
}