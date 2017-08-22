package server.database.builder;

import server.database.OracleDatabase;
import server.wiring.Wiring;

public class OracleDatabaseFactory extends AbstractDatabaseBuilder {

    public OracleDatabaseFactory(Wiring wiring) {
        super(wiring);
    }

    public OracleDatabase build() {
        return new OracleDatabase(wiring.databaseProperties());
    }
}
