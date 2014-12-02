package rest_tests;

import org.glassfish.jersey.client.ClientConfig;
import org.testng.annotations.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.testng.Assert.assertEquals;

public class NegativeFlow {

    public static final String TEST_URL = "http://localhost:8080";

    @Test
    public void testCacheMaxAge() {

        ClientConfig clientConfig = new ClientConfig();

        Client client = ClientBuilder.newClient(clientConfig);

        WebTarget target = client.target(TEST_URL);

        WebTarget cacheControl = target.path("blabla/blabla" + 10);

        Invocation.Builder invokeBuilder = cacheControl.request(MediaType.TEXT_PLAIN);

        invokeBuilder.header("some-header", "true");

        Response response = invokeBuilder.get();

        assertEquals(response.getStatus(), 404);

        System.out.println(response.readEntity(String.class));

    }


    @Test
    public void testHTTPMethods() {

        ClientConfig clientConfig = new ClientConfig();

        Client client = ClientBuilder.newClient(clientConfig);

        WebTarget target = client.target(TEST_URL);

        WebTarget cacheControl = target.path("blabla/blabla" + 10);

        Invocation.Builder invokeBuilder = cacheControl.request(MediaType.TEXT_PLAIN);

        invokeBuilder.header("some-header", "true");

        Response response = invokeBuilder.delete();

        assertEquals(response.getStatus(), 404);

        System.out.println(response.readEntity(String.class));

        System.out.println(response.getAllowedMethods());

        System.out.println(response.getCookies());

        System.out.println(response.getHeaders());

        System.out.println(response.getEntityTag());

        System.out.println(response.getMediaType());

    }
}
