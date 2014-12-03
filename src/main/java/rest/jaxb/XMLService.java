package rest.jaxb;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("xml/customer")
public class XMLService {


    @GET
    @Path("/{pin}")
    public Customer getCustomerInXML(@PathParam("pin") int pin) {
        Customer customer = new Customer();
        customer.setName("Sergii Oliinyk");
        customer.setPin(pin);

        return customer;
    }
}
