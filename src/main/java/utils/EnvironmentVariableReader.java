package utils;

public class EnvironmentVariableReader {

    public static String getEnvironment() {
        String environment = getVariable("ENVIRONMENT");
        return environment != null ? environment : "localhost";
    }

    public static String getAppRole() {
        String role = getVariable("ROLE");
        return role != null ? role : "READ";
    }

    private static String getVariable(String variable) {
        return System.getenv(variable);
    }
}
