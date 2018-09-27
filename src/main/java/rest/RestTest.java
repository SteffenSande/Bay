package rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by knutandersstokke on 27 27.09.2018.
 */

@Path("/test")
public class RestTest {

    @GET
    public Response test() {
        return Response.ok("Something").build();
    }

}
