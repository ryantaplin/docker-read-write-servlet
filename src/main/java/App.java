import server.wiring.Wiring;
import server.wiring.WiringImpl;
import setup.ServerSetup;

import static setup.DatabaseSetup.databaseSetupIsHealthy;
import static setup.ServerSetup.startServer;

public class App {

    private static Wiring wiring;

    public void main(String[] args) {
        System.out.printf("Application starting.%n%n");
        App.wiring = new WiringImpl();
        startApplication(1);
    }

    public void startApplication(int attempts) {
        int maxAttempts = wiring.databaseProperties().databaseMaxRetryAttempts();

        if (databaseSetupIsHealthy(wiring)) {
            startServer(wiring);
        } else if (attempts >= maxAttempts) {
            throw new RuntimeException("Database did not connect after " + maxAttempts + " attempts.");
        } else {
            System.out.println("Attempt " + attempts + " at starting the application.");
            retryDatabaseStartUp(attempts + 1);
        }
    }

    private void retryDatabaseStartUp(int attempt) {
        int retryTime = wiring.databaseProperties().databaseTimeout();
        System.out.printf(String.format("Database status failed... Retrying in %d seconds. %n", retryTime));
        scheduleRetry(attempt, retryTime);
    }

    private void scheduleRetry(int attempt, int retryTime) {
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
