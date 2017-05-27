package server;

import utils.PropertiesReader;

public class ServerSettings {
    private PropertiesReader propertiesReader;

    public ServerSettings(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

    public int serverPort() {
        return Integer.parseInt(propertiesReader.readProperty("server.port"));
    }
}