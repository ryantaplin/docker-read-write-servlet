package server.database.builder;

import server.database.ConnectionFactory;
import utils.properties.DatabaseProperties;

public abstract class AbstractDatabaseBuilder implements DatabaseBuilder {

    public DatabaseProperties databaseProperties;
    public ConnectionFactory connectionFactory;

    public AbstractDatabaseBuilder(DatabaseProperties databaseProperties, ConnectionFactory connectionFactory) {
        this.databaseProperties = databaseProperties;
        this.connectionFactory = connectionFactory;
    }

}
