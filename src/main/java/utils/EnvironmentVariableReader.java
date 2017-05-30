package utils;

public class EnvironmentVariableReader {

    public static String getEnvironment() {
        String environment = getVariable("ENVIRONMENT");
        return environment != null ? environment : "losthost";
    }

    private static String getVariable(String variable) {
        return System.getenv(variable);
    }
}
