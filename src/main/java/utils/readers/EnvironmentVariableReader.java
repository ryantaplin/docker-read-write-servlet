package utils.readers;

import server.wiring.Wiring;

public class EnvironmentVariableReader {

    private Wiring wiring;

    public EnvironmentVariableReader(Wiring wiring) {
        this.wiring = wiring;
    }

    public String getEnvironment() {
        String environment = getVariable("ENVIRONMENT");
        return environment != null ? environment.toLowerCase() : "localhost";
    }

    public String getAppRole() {
        String role = getVariable("ROLE");
        return role != null ? role.toLowerCase() : "READ";
    }

    private String getVariable(String variable) {
        return System.getenv(variable);
    }
}
