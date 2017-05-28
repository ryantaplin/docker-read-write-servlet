package server.servlets;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.BasicServer;
import server.ServerSettings;
import server.handlers.BasicHandlerBuilder;
import utils.EnvironmentVariableReader;
import utils.PropertiesReader;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReadyServletTest {

    private BasicServer server;

    @Before
    public void setUp() throws Exception {
        String environment = EnvironmentVariableReader.getSystemEnvironment();
        ServerSettings settings = new ServerSettings(new PropertiesReader(environment));

        server = new BasicServer(settings);
        server.withContext(new BasicHandlerBuilder().withReadyServlet().build());
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void readyServletReturnsOKWhenHappy() throws Exception {
        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8080/ready").openConnection();

        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
        assertThat("Content Type", http.getResponseMessage(), is("OK"));
    }

//    @Test
//    public void readyServletReturnFAILWhenSad() throws Exception {
//        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8080/ready").openConnection();
//
//        throw new UnsupportedOperationException("Not yet implemented failure for ready page.");
//        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.INTERNAL_SERVER_ERROR_500));
//        assertThat("Content Type", http.getResponseMessage(), is("FAIL"));
//    }
}