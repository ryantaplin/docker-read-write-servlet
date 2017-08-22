package server.jetty.servlets.model;

public class OracleProbe extends Probe {

    public OracleProbe(String status, String description) {
        super("Oracle Database", status, description);
    }
}
