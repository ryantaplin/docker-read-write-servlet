package server.database;

import server.wiring.Wiring;
import server.wiring.WiringImpl;

public class DatabaseBuilder {

    private Wiring wiring;

    public DatabaseBuilder(WiringImpl wiring) {
        this.wiring = wiring;
    }

    public MySQLDatabase build(String databaseName) {
        return new MySQLDatabase(wiring.databaseProperties(), databaseName);
    }
}
