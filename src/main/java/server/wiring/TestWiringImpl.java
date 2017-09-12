package server.wiring;

import server.database.Database;

//TODO Not sure what to do here; if i mock this then it's redundant but it's nice to have for awarness it is
//TODO using a test wiring; although if not mocked will use prod values.
public class TestWiringImpl extends WiringImpl {

    private Database database;

    public void setDatabase(Database database) {
        this.database = database;
    }

    @Override
    public Database database() {
        return this.database;
    }

//    private EnvironmentVariableReader environmentVariableReader;
//
//    @Override
//    public EnvironmentVariableReader environmentVariableReader() {
//        return environmentVariableReader;
//    }
//
//    public void setEnvironmentVariableReader(EnvironmentVariableReader environmentVariableReader) {
//        this.environmentVariableReader = environmentVariableReader;
//    }
}
