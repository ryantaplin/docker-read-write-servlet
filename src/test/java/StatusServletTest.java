import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.CapturedInputAndOutputs;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class StatusServletTest extends AbstractAcceptanceTest {

    private CloseableHttpResponse response;
    private String responseBody;

    //TODO: Mock database so I can edit probe responses

//    @Test
//    public void shouldReturnOKWhenAllProbesAreSuccessful() throws Exception {
//        when(weHitEndpoint("status"));
//        thenTheResponseCodeIs(200);
//        andTheResposeBodyIs("{\"Status\":\"FAIL\",\"Environment\":\"localhost\",\"probes\":[{\"Status\":\"FAIL\",\"Description\":\"[user=root][url=jdbc:mysql://192.168.127.128:3306/sky]\",\"Name\":\"MySQL sky Database\"}]}");
//    }

    @Test
    public void shouldReturnFailWhenOneOrMoreProbesFail() throws Exception {
        when(weHitEndpoint("status"));
        thenTheResponseCodeIs(200);
        andTheResposeBodyIs("{\"Status\":\"FAIL\",\"Environment\":\"localhost\",\"probes\":[{\"Status\":\"FAIL\",\"Description\":\"[user=root][url=jdbc:mysql://192.168.127.128:3306/sky]\",\"Name\":\"MySQL sky Database\"}]}");
    }

    private ActionUnderTest weHitEndpoint(String endpoint) throws IOException {
        String url = "http://localhost:8080/" + endpoint;
        return (interestingGivens, capturedInputAndOutputs) -> whenWeHitEndpoint(capturedInputAndOutputs, getRequestTo(url));
    }

    private CapturedInputAndOutputs whenWeHitEndpoint(CapturedInputAndOutputs capturedInputAndOutputs, CloseableHttpResponse url) throws IOException {
        response = url;
        responseBody = getResponseBody(url);
        capturedInputAndOutputs.add(String.format("Request from %s to %s", "User", "Server"), url);
        capturedInputAndOutputs.add(String.format("Response from %s to %s", "Server", "User"), responseBody);
        return capturedInputAndOutputs;
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