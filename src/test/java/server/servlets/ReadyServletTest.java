package server.servlets;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.BasicServer;
import server.ServerSettings;
import server.handlers.BasicHandlerBuilder;
import utils.EnvironmentVariableReader;
import utils.PropertiesReader;

import java.io.IOException;

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
        CloseableHttpResponse response = getRequestTo("http://localhost:8080/ready");
        int responseCode = response.getStatusLine().getStatusCode();
        String responseBody = EntityUtils.toString(response.getEntity());

        assertThat("Response Code", responseCode, is(HttpStatus.OK_200));
        assertThat("Content Type", responseBody, is("OK"));
    }

//    @Test
//    public void readyServletReturnFAILWhenSad() throws Exception {
//        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8080/ready").openConnection();
//
//        throw new UnsupportedOperationException("Not yet implemented failure for ready page.");
//        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.INTERNAL_SERVER_ERROR_500));
//        assertThat("Content Type", http.getResponseMessage(), is("FAIL"));
//    }

    public CloseableHttpResponse getRequestTo(String uri) throws IOException {
        HttpGet lukeRequest = new HttpGet(uri);
        return HttpClientBuilder.create().build().execute(lukeRequest);
    }
}