package utils.readers;

import static java.lang.System.getenv;

public class EnvironmentVariableReader {

    public String getEnvironment() {
        String environment = getEnvironmentVariable("ENVIRONMENT");
        return environment != null ? environment.toLowerCase() : "localhost";
    }

    public String getAppRole() {
        String appRole = getEnvironmentVariable("ROLE");
        return appRole == null ? "READ" : appRole.toUpperCase();
    }

    private String getEnvironmentVariable(String variable) {
        return getenv(variable);
    }
}
