package first;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpResponse;
import org.mockserver.verify.VerificationTimes;
import io.restassured.response.*;

import static org.mockserver.model.HttpRequest.request;

public class SixTest {
    private static final Logger userLogger = Logger.getLogger(SixTest.class);

    private ClientAndServer mockServer;

    @Before
    public void startServer() {
        mockServer = ClientAndServer.startClientAndServer(1080);
    }

    @Test
    public void testRequestOne() throws Exception {
        String URL = "http://localhost:8083";
        String path = "/users/new";
        mockServer.when(request().withPath("/test"))
                .respond(HttpResponse.response().withStatusCode(200).withBody("{\"token\":\"1234567890\"}"));

        /*new MockServerClient("localhost", 1080).verify(
                request()
                        .withMethod("GET")
                        .withPath(path),
                VerificationTimes.exactly(1)
        );*/

        RequestSpecification request = RestAssured.given();
        userLogger.info("RESTASSURED NOW WILL SEND REQUEST TO CONTROLLER ENDPOINT");
        userLogger.info("CONTROLLER URL: " + URL + path);
        request.baseUri(URL).basePath(path);
        Response response = request.when().get();
        userLogger.info("REST ASSURED SEND TEST REQUEST TO CONTROLLER AND GET RESPONSE CODE: " + response.getStatusCode());
        userLogger.info("BODY: " + response.getBody().asString());
    }

    @After
    public void stopServer() {
        mockServer.stop();
    }

}
