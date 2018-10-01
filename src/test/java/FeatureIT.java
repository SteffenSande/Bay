import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class FeatureIT {

    private Client client;
    private WebTarget target;
    private final URI uri = URI.create("http://localhost:8080/Bay/rest/auctions/");

    @BeforeEach
    void setUp() {
        this.client = ClientBuilder.newClient();
        this.target = this.client.target(uri);
    }

    @Test
    void auctionRestResponceWith200(){
        Response res = target.request().get();
        assertEquals(res.getStatus(), 200);
    }

    @Test
    void auctionRestGivesJsonProduct(){
        Response res = target.request().accept(MediaType.APPLICATION_JSON).get();
        assertTrue(res.getMediaType().toString().equals(MediaType.APPLICATION_JSON));
    }

    @Test
    void auctionRestGivesXMLProduct(){
        Response res = target.request().accept(MediaType.APPLICATION_XML).get();
        assertTrue(res.getMediaType().toString().equals(MediaType.APPLICATION_XML));
    }


}