package server.wiring;

import server.database.TestDatabase;
import server.database.repositories.StaffRepository;
import utils.properties.DatabaseProperties;
import utils.properties.ServerProperties;
import utils.readers.PropertiesReader;

public class TestWiring {

    public String environment = "acceptancetest";
    public String appRole = "READ";

    private PropertiesReader propertiesReader = new PropertiesReader(environment);
    public DatabaseProperties databaseProperties = new DatabaseProperties(propertiesReader);
    public ServerProperties serverProperties = new ServerProperties(propertiesReader);

    public TestDatabase database = new TestDatabase(databaseProperties);

    public StaffRepository staffRepository = new StaffRepository(database);

}
