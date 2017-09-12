package server.wiring;

import server.database.Database;
import server.database.OracleConnectionFactory;
import server.database.builder.OracleDatabaseFactory;
import server.database.repositories.StaffRepository;
import utils.properties.DatabaseProperties;
import utils.properties.ServerProperties;
import utils.readers.EnvironmentVariableReader;
import utils.readers.PropertiesReader;

public class TestWiring {

    private EnvironmentVariableReader envReader = new EnvironmentVariableReader();
    public String environment = envReader.getEnvironment();
    public String appRole = envReader.getAppRole();

    private PropertiesReader propertiesReader = new PropertiesReader(envReader.getEnvironment());
    public DatabaseProperties databaseProperties = new DatabaseProperties(propertiesReader);
    public ServerProperties serverProperties = new ServerProperties(propertiesReader);

    public OracleConnectionFactory oracleConnectionFactory = new OracleConnectionFactory(databaseProperties);
    public Database database = new OracleDatabaseFactory(databaseProperties, oracleConnectionFactory).build();

    public StaffRepository staffRepository = new StaffRepository(database);

}
