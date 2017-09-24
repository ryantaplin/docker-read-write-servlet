package server.database.builder;

import server.database.OracleDatabase;
import utils.properties.DatabaseProperties;

public class OracleDatabaseFactory extends AbstractDatabaseBuilder {

    public OracleDatabaseFactory(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }

    public OracleDatabase build() {
        return new OracleDatabase(databaseProperties);
    }
}
