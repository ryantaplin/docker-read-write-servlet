package server.wiring;

import server.database.repositories.StaffRepository;
import utils.properties.DatabaseProperties;
import utils.properties.ServerProperties;
import server.database.Database;

public interface Wiring {

    String environment();

    String appRole();

    ServerProperties serverProperties();

    DatabaseProperties databaseProperties();

    Database database();

    StaffRepository staffRepository();

}
