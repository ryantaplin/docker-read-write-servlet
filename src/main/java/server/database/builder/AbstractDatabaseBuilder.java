package server.database.builder;

import utils.properties.DatabaseProperties;

public abstract class AbstractDatabaseBuilder implements DatabaseBuilder {

    public DatabaseProperties databaseProperties;

    public AbstractDatabaseBuilder(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
