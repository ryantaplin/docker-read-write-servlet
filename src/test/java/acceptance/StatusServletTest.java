package acceptance;

import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.CapturedInputAndOutputs;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import server.database.OracleDatabase;
import server.jetty.servlets.model.probes.Probe;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;


public class StatusServletTest extends AbstractAcceptanceTest {

    private CloseableHttpResponse response;
    private String responseBody;

    private OracleDatabase database = mock(OracleDatabase.class);

    @Test
    public void shouldReturnFailWhenOneOrMoreProbesFail() throws Exception {
        givenDatabaseProbeIsNotSuccessful();
        givenTheServerIsRunning();

        when(whenWeHitEndpoint("status"));

        thenTheResponseCodeIs(200);
        andTheResposeBodyIs("{\"Status\":\"FAIL\",\"Environment\":\"acceptancetest\",\"probes\":[{\"Status\":\"FAIL\",\"Description\":\"[user=root][url=jdbc:mysql://db/test]\",\"Name\":\"Test Database\"}]}");
    }

    @Test
    public void shouldReturnOKWhenAllProbesAreSuccessful() throws Exception {
        givenDatabaseProbeIsSuccessful();
        givenTheServerIsRunning();

        when(whenWeHitEndpoint("status"));

        thenTheResponseCodeIs(200);
        andTheResposeBodyIs("{\"Status\":\"OK\",\"Environment\":\"acceptancetest\",\"probes\":[{\"Status\":\"OK\",\"Description\":\"[user=root][url=jdbc:mysql://db/test]\",\"Name\":\"Test Database\"}]}");
    }

    private ActionUnderTest whenWeHitEndpoint(String endpoint) throws IOException {
        String url = "http://localhost:" + wiring.serverProperties().serverPort() + "/" + endpoint;
        return (interestingGivens, capturedInputAndOutputs) -> whenWeHitEndpoint(capturedInputAndOutputs, getRequestTo(url));
    }

    private CapturedInputAndOutputs whenWeHitEndpoint(CapturedInputAndOutputs capturedInputAndOutputs, CloseableHttpResponse url) throws IOException {
        response = url;
        responseBody = getResponseBody(url);
        capturedInputAndOutputs.add(String.format("Request from %s to %s", "User", "Server"), url);
        capturedInputAndOutputs.add(String.format("Response from %s to %s", "Server", "User"), responseBody);
        return capturedInputAndOutputs;
    }

    private void givenDatabaseProbeIsSuccessful() {
        BDDMockito.when(database.probe()).thenReturn(new Probe("Test Database", "OK", "[user=root][url=jdbc:mysql://db/test]"));
        testWiring.database = database;
    }

    private void givenDatabaseProbeIsNotSuccessful() {
        BDDMockito.when(database.probe()).thenReturn(new Probe("Test Database", "FAIL", "[user=root][url=jdbc:mysql://db/test]"));
        testWiring.database = database;
    }

    private void thenTheResponseCodeIs(int responseCode) {
        assertThat("Response Code", getResponseCode(response), is(responseCode));
    }

    private void andTheResposeBodyIs(String response) throws IOException {
        assertThat("Content Type", responseBody, is(response));
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