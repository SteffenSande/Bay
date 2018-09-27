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
import entities.Product;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Path("/auctions") //Returns a representation with references to all current auctions (ongoing/completed) in the system
@Stateless
public class RestService {

	@EJB
	private AuctionDao em;

    //return a representation with references to all current auctions (ongoing/completed) in the system.
    @GET
    public Response getAllProducts(){
	    List<Product> products = em.getAllProducts();
	    return Response.ok(products.toString()).build();
    }


    //returns a representation of the auction/product identified by id
    @GET
    @Path("/{productID: \\d+}")
    public Response getProductsByID(@PathParam("productID") int id){
        Product p = em.getProductByID(id);
        return Response.ok(p.toString()).build();
    }

    //return a representation with reference to all current bids in the auction identified by id
    @GET
    @Path("/{productID : \\d+}/bids/")
    public Response bi(@PathParam("productID") int id){
        Product p = em.getProductByID(id);
        List<Bid> bids = p.getBids();
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
