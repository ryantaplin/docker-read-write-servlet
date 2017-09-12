package server.wiring;

import server.database.Database;
import server.database.builder.OracleDatabaseFactory;
import server.database.repositories.StaffRepository;
import utils.properties.DatabaseProperties;
import utils.properties.ServerProperties;
import utils.readers.EnvironmentVariableReader;
import utils.readers.PropertiesReader;

public class DefaultWiring {

    private static final EnvironmentVariableReader envReader = new EnvironmentVariableReader();
    public static final String environment = envReader.getEnvironment();
    public static final String appRole = envReader.getAppRole();

    private static final PropertiesReader propertiesReader = new PropertiesReader(envReader.getEnvironment());
    public static final DatabaseProperties databaseProperties = new DatabaseProperties(propertiesReader);
    public static final ServerProperties serverProperties = new ServerProperties(propertiesReader);

    public static final Database database = new OracleDatabaseFactory(databaseProperties).build();

    public static final StaffRepository staffRepository = new StaffRepository(database);
}
