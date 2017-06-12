package server.servlets;

import com.googlecode.yatspec.junit.SpecRunner;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import server.BasicServer;
import server.handlers.WriteHandlerBuilder;

import java.io.IOException;

import static org.eclipse.jetty.http.HttpStatus.OK_200;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static utils.Settings.getServerSettings;


@RunWith(SpecRunner.class)
public class ReadyServletTest {

    private BasicServer server;
    private CloseableHttpResponse response;

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
    public void shouldReturnOK() throws Exception {
        whenWeHitEndpoint("ready");
        thenTheResponseCodeIs(200);
        andTheResposeBodyIs("OK");
    }

    private void thenTheResponseCodeIs(int responseCode) {
        assertThat("Response Code", getResponseCode(response), is(responseCode));
    }

    private void andTheResposeBodyIs(String responseBody) throws IOException {
        assertThat("Content Type", getResponseBody(response), is(responseBody));
    }

    private void whenWeHitEndpoint(String endpoint) throws IOException {
        String url = "http://localhost:8080/" + endpoint;
        response = getRequestTo(url);
    }

    private int getResponseCode(CloseableHttpResponse response) {
        return response.getStatusLine().getStatusCode();
    }

    private String getResponseBody(CloseableHttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity());
    }

    private CloseableHttpResponse getRequestTo(String uri) throws IOException {
        HttpGet request = new HttpGet(uri);
        return HttpClientBuilder.create().build().execute(request);
    }
}