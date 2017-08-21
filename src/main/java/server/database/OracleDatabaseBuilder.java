package server.database;

import server.wiring.Wiring;

public class OracleDatabaseBuilder extends AbstractDatabaseBuilder {

    public OracleDatabaseBuilder(Wiring wiring) {
        super(wiring);
    }

    public OracleDatabase build(String databaseName) {
        return new OracleDatabase(wiring.databaseProperties(), databaseName);
    }
}
