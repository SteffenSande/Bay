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
    public Response getProductsByID(@PathParam("productID") int aid){
        Product p= em.getProductByID(aid);
        return Response.ok(p.toString()).build();
    }

    //return a representation with reference to all current bids in the auction identified by id
    @GET
    @Path("/{productID : \\d+}/bids/") 
    public Response showBidsOnProduct(@PathParam("productID") int aid){
        List<Bid> bids = em.getAllBidsForProduct(aid); //p.getBids
        String result = "";
        for (int i = 0; i < bids.size(); i++) {
			result += bids.get(i).toString();
			
			if(i != bids.size()-1) {
				result += '\n';
			}
		}
        
        return Response.ok(result).build();
    }

    //returns a representation of the given bid within the auction identified by "aid" and "bidId"
    @GET
    @Path("/{productID : \\d+}/bids/{bidID : \\d+}")
    public Response getSpecificBid(@PathParam("productID") int aid, @PathParam("productID") int bidID){
        Bid bid = em.getSpecificBidOnProduct(aid, bidID);
        return Response.ok(bid.toString()).build();
    }

  /*  <app-path>/res/auction/{id}/bids - which creates a bid with a specified amount in the auction
    identified by id and returns a representation of the bid. The amount should be contained
    in the payload of the request (or optionally as a query parameter).*/

    @POST
    @Path("/{productID : \\d+}/bids/{bidAmount : \\d+}")
    public Response bidOnAProduct(@PathParam("productID") int aid, @PathParam("bidAmount") int bidAmount){
        Product p = em.getProductByID(aid);
        Bid bid = new Bid();
        bid.setValue(bidAmount);
        p.setBid(bid);
        return Response.ok(bid.toString()).build();

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
