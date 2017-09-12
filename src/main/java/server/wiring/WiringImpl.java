package server.wiring;

import utils.properties.DatabaseProperties;
import utils.properties.ServerProperties;
import server.database.Database;
import server.database.repositories.StaffRepository;

public class WiringImpl implements Wiring {

    private ServerProperties serverProperties;
    private DatabaseProperties databaseProperties;
    private Database database;
    private String environment;
    private String appRole;
    private StaffRepository staffRepository;

    public WiringImpl(String environment,
                      String appRole,
                      ServerProperties serverProperties,
                      DatabaseProperties databaseProperties,
                      Database database,
                      StaffRepository staffRepository) {
        this.environment = environment;
        this.appRole = appRole;
        this.serverProperties = serverProperties;
        this.databaseProperties = databaseProperties;
        this.database = database;
        this.staffRepository = staffRepository;
    }

    public String environment() {
        return this.environment;
    }

    public String appRole() {
        return this.appRole;
    }

    public ServerProperties serverProperties() {
        return serverProperties;
    }

    public DatabaseProperties databaseProperties() {
        return databaseProperties;
    }

    public Database database() {
        return database;
    }

    public StaffRepository staffRepository() {
        return staffRepository;
    }
}
