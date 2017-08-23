package server.database;

import oracle.jdbc.pool.OracleDataSource;
import utils.properties.DatabaseProperties;

import java.sql.SQLException;

public class OracleDataSourceFactory {

    private DatabaseProperties properties;

    public OracleDataSourceFactory(DatabaseProperties properties) {
        this.properties = properties;
    }

    public OracleDataSource build() {
        try {
            return getOracleDataSource();
        } catch (SQLException e) {
            throw new RuntimeException("Can't create OracleDataSource for database: " + properties.databaseURL());
        }
    }

    private OracleDataSource getOracleDataSource() throws SQLException {
        OracleDataSource oracleDataSource = new OracleDataSource();
        oracleDataSource.setURL(properties.databaseURL());
        oracleDataSource.setUser(properties.databaseUsername());
        oracleDataSource.setPassword(properties.databasePassword());
        return oracleDataSource;
    }
}
