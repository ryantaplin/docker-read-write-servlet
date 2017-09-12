package server.database;

import oracle.jdbc.driver.OracleConnection;

//TODO: ADD THIS
public interface ConnectionFactory {
    OracleConnection create();
}
