package server.database;

import oracle.jdbc.driver.OracleConnection;


public interface ConnectionFactory {
    OracleConnection create();
}
