package Utils.Properties;

public class EnvironmentVariableReader {

    public static String getEnvironment() {
        String environment = System.getenv("ENVIRONMENT"); //extract environmentVariable out?

        if (environment == null)
            throw new NullPointerException("No ENVIRONMENT variable found.");

        return environment;
    }
}
