package server.database.builder;

import server.database.OracleDatabase;
import utils.properties.DatabaseProperties;

public class OracleDatabaseBuilder extends AbstractDatabaseBuilder {

    public OracleDatabaseBuilder(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }

    public OracleDatabase build() {
        return new OracleDatabase(databaseProperties);
    }
}
