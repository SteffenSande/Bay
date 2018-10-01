import static org.junit.jupiter.api.Assertions.assertTrue;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;

import rest.RestService;

import javax.ws.rs.core.Application;

public class Tests extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(RestService.class);
    }

    @Test
    public void ordersPathParamTest() {
        String response = target("/auctions").request().get(String.class);
        System.out.println(response);
        assertTrue("orderId: 453".equals(response));
    }


}
