package properties;

import utils.readers.PropertiesReader;

abstract class Properties {

    public PropertiesReader propertiesReader;

    public Properties(PropertiesReader propertiesReader) {
        this.propertiesReader = propertiesReader;
    }

}
