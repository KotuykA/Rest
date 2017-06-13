package rest_tests;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rest.jaxb.Customer;
import rest.jaxb.CustomerRequest;
import rest.jaxb.CustomerResponse;
import rest.json.Track;
import rest.json.TrackRequest;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PositiveFlow {

    public static final String TEST_URL = "http://localhost:8080/rest";

    public static final Logger LOGGER = Logger.getLogger(PositiveFlow.class.getName());

    ClientConfig clientConfig;

    Client client;

    WebTarget target;

    @BeforeTest
    public void setUp() {

        clientConfig = new ClientConfig();

        clientConfig.register(new LoggingFilter(LOGGER, true));

        client = ClientBuilder.newClient(clientConfig);

        target = client.target(TEST_URL);
    }


    @Test
    public void testCacheMaxAge() {

        WebTarget cacheControl = target.path("cache-control/" + 10);

        Invocation.Builder invokeBuilder = cacheControl.request(MediaType.TEXT_PLAIN);

        invokeBuilder.header("some-header", "true");

        Response response = invokeBuilder.get();

        assertEquals(response.getStatus(), 200);

        System.out.println(response.readEntity(String.class));
    }


    @Test
    public void testQueryParam() {

        WebTarget queryURL = target.path("users/queryparam/query");

        WebTarget queryURLWithParam = queryURL.queryParam("from", "20").queryParam("to", "100");

        Invocation.Builder invokeBuilder = queryURLWithParam.request(MediaType.TEXT_PLAIN);

        invokeBuilder.header("some-header", "true");

        Response response = invokeBuilder.get();

        assertEquals(response.getStatus(), 200);

        System.out.println(response.readEntity(String.class));
    }

    @Test
    public void testJSONGet() throws IOException {

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


    @Test
    public void testJSONPost() throws IOException {

        String input = "{\"group\":\"Linkin Park\",\"title\":\"Numb\"}";

        //        Track trackNew = new Track();
//        trackNew.setTitle("Numb");
//        trackNew.setGroup("Linkin Park");

        WebTarget queryURL = target.path("json/post");

        Track track = queryURL.request(MediaType.APPLICATION_JSON).post(Entity.entity(input, MediaType.APPLICATION_JSON), Track.class);

        assertTrue(track.getTitle().equals("Numb"));

        assertTrue(track.getGroup().equals("Linkin Park"));
    }

    @Test
    public void testJSONPostTEST() throws IOException {

        TrackRequest trackRequest = new TrackRequest();
        trackRequest.setID(54);

        WebTarget queryURL = target.path("json/post/track12");

        Track track = queryURL.request(MediaType.APPLICATION_JSON).post(Entity.entity(trackRequest, MediaType.APPLICATION_JSON), Track.class);

        assertTrue(track.getTitle().equals("Ocean"));

        assertTrue(track.getGroup().equals("Steel"));
    }

    @Test
    public void testXMlGet() throws IOException {
        int id = 1;
        WebTarget queryURL = target.path("xml/customer/" + id);
        Customer customer = queryURL.request(MediaType.APPLICATION_XML).get(Customer.class);
        assertTrue(customer.getName().equals("Sergii Oliinyk"));
    }

    @Test
    public void testXMlPost() throws IOException {

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setID(2);

        WebTarget queryURL = target.path("xml/customer/customerRequest");
        CustomerResponse customerResponse = queryURL.request(MediaType.APPLICATION_XML).post(Entity.entity(customerRequest, MediaType.APPLICATION_XML), CustomerResponse.class);
        assertTrue(customerResponse.getName().equals("Anton"));
    }
}

