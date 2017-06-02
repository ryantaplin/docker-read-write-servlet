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
import server.handlers.HandlerBuilder;
import server.handlers.WriteHandlerBuilder;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static utils.Settings.getServerSettings;

public class ReadyServletTest {

    private BasicServer server;

    @Before
    public void setUp() throws Exception {
        server = new BasicServer(getServerSettings());
        server.withContext(new WriteHandlerBuilder().build());
        server.start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void readyServletReturnsOKWhenHappy() throws Exception {
        CloseableHttpResponse response = getRequestTo("http://localhost:8080/ready");

        assertThat("Response Code", getResponseCode(response), is(HttpStatus.OK_200));
        assertThat("Content Type", getResponsBody(response), is("OK"));
    }

    private int getResponseCode(CloseableHttpResponse response) {
        return response.getStatusLine().getStatusCode();
    }

    private String getResponsBody(CloseableHttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity());
    }

    private CloseableHttpResponse getRequestTo(String uri) throws IOException {
        HttpGet request = new HttpGet(uri);
        return HttpClientBuilder.create().build().execute(request);
    }
}