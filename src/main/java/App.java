import server.wiring.Wiring;
import server.wiring.WiringImpl;

import static setup.DatabaseSetup.databaseSetupIsHealthy;
import static setup.ServerSetup.startServer;

public class App {

    private static Wiring wiring;

    public void main(String[] args) {
        System.out.printf("Application starting.%n%n");
        startApplication(new WiringImpl());
    }

    private void startApplication(Wiring wiring) {
        App.wiring = wiring;
        attemptDatabaseConnection(1);
    }

    private static void attemptDatabaseConnection(int attempts) {
        int maxAttempts = wiring.databaseProperties().databaseMaxRetryAttempts();

        if (databaseSetupIsHealthy()) {
            startServer(wiring);
        } else if (attempts >= maxAttempts) {
            throw new RuntimeException("Database did not connect after " + maxAttempts + " attempts.");
        } else {
            System.out.println("Attempt " + attempts + " at starting the application.");
            retryDatabaseStartUp(attempts + 1);
        }
    }

    private static void retryDatabaseStartUp(int attempt) {
        int retryTime = wiring.databaseProperties().databaseTimeout();
        System.out.printf(String.format("Database status failed... Retrying in %d seconds. %n", retryTime));
        scheduleRetry(attempt, retryTime);
    }

    private static void scheduleRetry(int attempt, int retryTime) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        attemptDatabaseConnection(attempt);
                    }
                },
                retryTime * 100
        );
    }
}
