package rest_tests;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;
import org.testng.annotations.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PositiveFlow {

    public static final String TEST_URL = "http://localhost:8080";

    @Test
    public void testCacheMaxAge() {

        ClientConfig clientConfig = new ClientConfig();

        Client client = ClientBuilder.newClient(clientConfig);

        WebTarget target = client.target(TEST_URL);

        WebTarget cacheControl = target.path("cache-control/" + 10);

        Invocation.Builder invokeBuilder = cacheControl.request(MediaType.TEXT_PLAIN);

        invokeBuilder.header("some-header", "true");

        Response response = invokeBuilder.get();

        assertEquals(response.getStatus(), 200);

        System.out.println(response.readEntity(String.class));
    }


    @Test
    public void testQueryParam() {

        ClientConfig clientConfig = new ClientConfig();

        Client client = ClientBuilder.newClient(clientConfig);

        WebTarget target = client.target(TEST_URL);

        WebTarget queryURL = target.path("users/queryparam/query");

        WebTarget queryURLWithParam = queryURL.queryParam("from", "20").queryParam("to", "100");

        Invocation.Builder invokeBuilder = queryURLWithParam.request(MediaType.TEXT_PLAIN);

        invokeBuilder.header("some-header", "true");

        Response response = invokeBuilder.get();

        assertEquals(response.getStatus(), 200);

        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void testJSON() throws IOException {

        ClientConfig clientConfig = new ClientConfig();

        Client client = ClientBuilder.newClient(clientConfig);

        WebTarget target = client.target(TEST_URL);

        WebTarget queryURL = target.path("json/get");

        Invocation.Builder invokeBuilder = queryURL.request(MediaType.APPLICATION_JSON);

        Response response = invokeBuilder.get();

        assertEquals(response.getStatus(), 200);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, String> jsonResult = objectMapper.readValue(response.readEntity(String.class),
                new TypeReference<HashMap<String, String>>() {
                });

        assertTrue(jsonResult.get("title").equals("Diesel power"));
        assertTrue(jsonResult.get("group").equals("Prodigy"));
    }
}

