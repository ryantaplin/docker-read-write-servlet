package utils;

public class EnvironmentVariableReader {

    public static String getEnvironment() {
        String environment = getVariable("ENVIRONMENT");
        return environment != null ? environment.toLowerCase() : "localhost";
    }

    public static String getAppRole() {
        String role = getVariable("ROLE");
        return role != null ? role.toLowerCase() : "read";
    }

    private static String getVariable(String variable) {
        return System.getenv(variable);
    }
}
