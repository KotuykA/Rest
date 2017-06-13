package rest.jaxb;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customerRequest")
public class CustomerRequest {

    int id;

        @XmlElement
        public int getID() {
            return id;
        }

        public void setID(int id) {
            this.id = id;
        }

}
