package settings;

import utils.PropertiesReader;

public class ServerSettings extends Settings {

    public ServerSettings(PropertiesReader propertiesReader) {
        super(propertiesReader);
    }

    public int serverPort() {
        return Integer.parseInt(propertiesReader.readProperty("server.port"));
    }
}