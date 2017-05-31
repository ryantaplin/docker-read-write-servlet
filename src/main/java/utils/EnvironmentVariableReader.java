package utils;

public class EnvironmentVariableReader {

    public static String getEnvironment() {
        String environment = getVariable("ENVIRONMENT");
        return environment != null ? environment : "losthost";
    }

    public static String getAppRole() {
        String role = getVariable("ROLE");
        return role != null ? "WRITE" : "READ";
    }

    private static String getVariable(String variable) {
        return System.getenv(variable);
    }
}
