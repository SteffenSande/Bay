package rest;

import entities.Bid;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import dao.AuctionDao;
import entities.Feedback;
import entities.Product;

import java.net.URI;
import java.util.Date;
import java.util.List;


/** Returns a representation with references to all current auctions (ongoing/completed) in the system */
@Path("auctions")
@Stateless
public class RestService {

	@Inject
	AuctionDao auctionDao;

    /** return a representation with references to all current auctions (ongoing/completed) in the system. */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllProducts() {
	    List<Product> products = auctionDao.getAllProducts();
	    return Response.ok().entity(new GenericEntity<List<Product>>(products){}).build();


	    //return Response.ok(products.toString()).build();
    }

    /** returns a representation of the auction/product identified by id */
    @GET
    @Path("/{productID: \\d+}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response getProductsByID(@PathParam("productID") int id){
        Product p = auctionDao.getProductByID(id);
        return Response.ok().entity(p).build();
    }

    /** return a representation with reference to all current bids in the auction identified by id */
    @GET
    @Path("/{productID : \\d+}/bids/")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response showBidsOnProduct(@PathParam("productID") int aid){
        List<Bid> bids = auctionDao.getAllBidsForProduct(aid); //p.getBids();
        GenericEntity<List<Bid>> genericList = new GenericEntity<List<Bid>>(bids) {};
        return Response.ok().entity(genericList).build();
    }

    /** returns a representation of the given bid within the auction identified by "aid" and "bidId" */
    @GET
    @Path("/{productID : \\d+}/bids/{bidID : \\d+}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response getSpecificBid(@PathParam("productID") int aid, @PathParam("bidID") int bidID){
        Bid bid = auctionDao.getSpecificBidOnProduct(bidID);
        return Response.ok().entity(bid).build();
    }

    /** creates a bid with a specified amount in the auction
    identified by id and returns a representation of the bid. The amount should be contained
    in the payload of the request (or optionally as a query parameter).*/

    @POST
    @Path("/{productID : \\d+}/bids/{bidAmount : \\d+}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response bidOnAProduct(@PathParam("productID") int aid, @PathParam("bidAmount") int bidAmount){
        Product p = auctionDao.getProductByID(aid);
        Bid bid = new Bid();
        bid.setValue(bidAmount);
        bid.setTime(new Date());
        auctionDao.persist(bid);
        p.setBid(bid);
        URI uri = UriBuilder.fromUri("http://localhost:8080/Bay/rest/auctions").path(Integer.toString(aid)).path(Integer.toString(bidAmount)).build();
        return Response.created(uri).entity(bid).build();
    }

    /** This is for test purposes only, as it doesn't use AuctionDao. When everyone is comfortable with testing,
      * this method can be removed, though it wont do any harm.
     */
    @GET
    @Path("/testFeedback")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response testFeedback() {
        return Response.ok(new Feedback("Content", 4, new Date())).build();
    }
}
