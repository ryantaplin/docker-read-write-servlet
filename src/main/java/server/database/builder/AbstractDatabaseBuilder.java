package server.database.builder;

import server.wiring.Wiring;

public abstract class AbstractDatabaseBuilder implements DatabaseBuilder {

    public Wiring wiring;

    AbstractDatabaseBuilder(Wiring wiring) {
        this.wiring = wiring;
    }


}
