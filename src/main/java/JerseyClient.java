import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class JerseyClient {

    public static final String TEST_URL = "http://localhost:8080/entry-point/test";

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();

        String result = client.target(TEST_URL).request(MediaType.TEXT_PLAIN).get(String.class);

        System.out.println(result);
    }
}
