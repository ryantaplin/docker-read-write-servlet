package server.wiring;

import server.database.Database;
import utils.readers.EnvironmentVariableReader;

public class TestWiringImpl extends WiringImpl {

    private EnvironmentVariableReader environmentVariableReader = null;
    private Database database = null;

    @Override
    public Database database() {
        return database;
    }

    @Override
    public EnvironmentVariableReader environmentVariableReader() {
        return environmentVariableReader;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void setEnvironmentVariableReader(EnvironmentVariableReader environmentVariableReader) {
        this.environmentVariableReader = environmentVariableReader;
    }
}
