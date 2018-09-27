package rest;

import entities.Bid;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejb.AuctionDao;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Path("/bi")
//@Path("/Bid") //root resource class must have public constructor
@Stateless
public class RestService {

	@EJB
	private AuctionDao em;
	
	
    @GET
    public Response bi	(){
        List<Bid> bids = em.getAllBids();
        bids.sort(null);
        return Response.ok(bids.toString()).build();
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
