package server.database.builder;

import server.database.OracleConnectionFactory;
import server.database.OracleDatabase;
import utils.properties.DatabaseProperties;

public class OracleDatabaseFactory extends AbstractDatabaseBuilder {

    public OracleDatabaseFactory(DatabaseProperties databaseProperties, OracleConnectionFactory oracleConnectionFactory) {
        super(databaseProperties, oracleConnectionFactory);
    }

    public OracleDatabase build() {
        return new OracleDatabase(databaseProperties, connectionFactory);
    }
}
