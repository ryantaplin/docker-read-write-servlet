package utils.readers;

import server.jetty.servlets.model.AppRole;
import server.wiring.Wiring;

import static server.jetty.servlets.model.AppRole.*;

public class EnvironmentVariableReader {

    private Wiring wiring;

    public EnvironmentVariableReader(Wiring wiring) {
        this.wiring = wiring;
    }

    public String getEnvironment() {
        String environment = getVariable("ENVIRONMENT");
        return environment != null ? environment.toLowerCase() : "localhost";
    }

    public AppRole getAppRole() {
        try {
            return valueOf(getVariable("ROLE").toUpperCase());
        } catch (Exception e) {
            System.out.println("Environment variable app role not found - setting it to default");
        }
        return READ;
    }

    private String getVariable(String variable) {
        return System.getenv(variable);
    }
}
