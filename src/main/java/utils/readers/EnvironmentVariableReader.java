package utils.readers;

import server.jetty.servlets.model.AppRole;
import server.wiring.Wiring;

import static java.lang.System.getenv;
import static server.jetty.servlets.model.AppRole.*;

public class EnvironmentVariableReader {

    public String getEnvironment() {
        String environment = getEnvironmentVariable("ENVIRONMENT");
        return environment != null ? environment.toLowerCase() : "localhost";
    }

    //TODO make default value a configurable property?
    public AppRole getAppRole() {
        String role = getEnvironmentVariable("ROLE").toUpperCase();
        try {
            return valueOf(role);
        } catch (Exception e) {
            System.out.println("ROLE could not be recognised: " + role);
            System.out.println("App role has been set to default value.");
        }
        return READ;
    }

    private String getEnvironmentVariable(String variable) {
        return getenv(variable);
    }
}
