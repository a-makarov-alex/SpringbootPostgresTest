package first;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpResponse;
import org.mockserver.verify.VerificationTimes;
import io.restassured.response.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

        RequestSpecification request = RestAssured.given();
        userLogger.info("TEST REQUEST TO CONTROLLER BY URL: " + URL + path);
        request.baseUri(URL).basePath(path);
        Response response = request.when().get();
        userLogger.info("REST ASSURED SEND TEST REQUEST TO CONTROLLER AND GET RESPONSE CODE: " + response.getStatusCode());
        userLogger.info("BODY: " + response.getBody().asString());
    }

    /**
     * Тест с использованием json файла в качестве expectation body для mockserver
     */
    @Test
    public void testWithJSONExpectation() {
        /*String jsonPath = "src/test/resources/json/testexpectations";
        mockServer.when(request().withPath("/test"))
                .respond(HttpResponse.response().withStatusCode(200).withBody("{\"token\":\"1234567890\"}"));*/

        //ConfigurationProperties.initializationJsonPath(jsonPath);
        String URL = "http://localhost:8083";
        String pathOne = "/users/expect";

        /**
         * Указываем заглушку для GET эндпойнта
         */
        String pathGet = "src/test/resources/expectations/get.users.json";
        try {
            String expectGetBody = new String(Files.readAllBytes(Paths.get(pathGet)));
            //userLogger.info(expectGetBody);
            mockServer.when(request().withMethod("get").withPath("/test/one"))
                    .respond(HttpResponse.response().withStatusCode(200).withBody(expectGetBody));
        } catch(IOException e) {
            userLogger.info("IOException " + e);
        }

        RequestSpecification request = RestAssured.given();
        userLogger.info("TEST REQUEST TO CONTROLLER BY URL: " + URL + pathOne);
        request.baseUri(URL).basePath(pathOne).queryParam("expectValue", "one");
        Response response = request.when().get();
        userLogger.info("REST ASSURED SEND TEST REQUEST TO CONTROLLER AND GET RESPONSE CODE: " + response.getStatusCode());
        userLogger.info("BODY: " + response.getBody().asString());

    }

    @After
    public void stopServer() {
        mockServer.stop();
    }

}
