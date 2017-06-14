import static setup.DatabaseSetup.databaseSetupIsHealthy;
import static setup.ServerSetup.startServer;
import static utils.Settings.getDatabaseSettings;

public class App {

    public static void main(String[] args) {
        System.out.printf("Application starting.%n%n");
        startApplication(1);
    }

    private static void startApplication(int attempts) {
        int maxAttempts = getDatabaseSettings().databaseMaxRetryAttempts();

        if (attempts >= maxAttempts)
            throw new RuntimeException("Database did not connect after " + maxAttempts + " attempts.");

        if (databaseSetupIsHealthy()) {
            startServer();
        } else {
            System.out.println("Attempt " + attempts + " at starting the application.");
            retryApplicationStartUp(attempts + 1);
        }
    }

    private static void retryApplicationStartUp(int attempt) {
        int retryTime = getDatabaseSettings().databaseTimeout();
        System.out.printf(String.format("Database status failed... Retrying in %d seconds. %n", retryTime));
        scheduleRetry(attempt, retryTime);
    }

    private static void scheduleRetry(int attempt, int retryTime) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        startApplication(attempt);
                    }
                },
                retryTime * 100
        );
    }
}
