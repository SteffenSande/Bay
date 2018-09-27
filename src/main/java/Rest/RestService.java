package Rest;

import entities.Bid;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/bids")
//@Path("/Bid") //root resource class must have public constructor
@Stateless
public class RestService {

    @PersistenceContext(unitName = "Bay")
    private EntityManager em;

    @GET
    public Response getBids(){
        try {


            TypedQuery<Bid> query = em.createNamedQuery(Bid.FIND_ALL, Bid.class);
            List<Bid> bids = query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return Response.ok().build();
    }

    /*

    @GET
    @Produces(MediaType.APPLICATION_XML)
    //@Produces(MediaType.TEXT_HTML)
    //@Path("/all")

    public Response getBids(){
        TypedQuery<Bid> query = em.createNamedQuery(Bid.FIND_ALL, Bid.class);
        List<Bid> bids = query.getResultList();
        return Response.ok(bids).build();
    }
    @GET
    @Path("{id}")
    public Response getBid(@PathParam("id") String id){
        int idInt = Integer.parseInt(id);
        Bid bid = em.find(Bid.class, idInt);
        if(bid == null){
            throw new NotFoundException();
        }
        return Response.ok(bid).build();
    }*/
}
