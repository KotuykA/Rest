import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class JerseyClient {

    public static final String TEST_URL = "http://localhost:8080/cache-control/11/12";

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();

        try {

            String result = client.target(TEST_URL).request(MediaType.TEXT_PLAIN).get(String.class);
        } catch (NotFoundException e){
            System.out.println(e.getCause());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getResponse().getMediaType());
            System.out.println(e.getResponse().getEntity());
        }


    }
}
