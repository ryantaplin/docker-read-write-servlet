package settings;

import utils.PropertiesReader;

abstract class Settings {
    PropertiesReader propertiesReader;

    Settings(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }
}
