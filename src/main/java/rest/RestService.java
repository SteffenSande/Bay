package rest;

import entities.Bid;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejb.AuctionDao;
import entities.Product;

import java.util.List;


@Path("auctions") //Returns a representation with references to all current auctions (ongoing/completed) in the system
@Stateless
public class RestService {

	@EJB
	private AuctionDao auctionDao;

    /** return a representation with references to all current auctions (ongoing/completed) in the system. */
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Product> getAllProducts(){
	    List<Product> products = auctionDao.getAllProducts();
	    return products;
    }

    /** returns a representation of the auction/product identified by id */
    @GET
    @Path("/{productID: \\d+}")
    public Response getProductsByID(@PathParam("productID") int id){
        Product p = auctionDao.getProductByID(id);
    public Response getProductsByID(@PathParam("productID") int aid){
        Product p = em.getProductByID(aid);
        return Response.ok(p.toString()).build();
    }

    /** return a representation with reference to all current bids in the auction identified by id */
    @GET
    @Path("/{productID : \\d+}/bids/")
    public Response bi(@PathParam("productID") int id){
        Product p = auctionDao.getProductByID(id);
        List<Bid> bids = p.getBids();
    public Response showBidsOnProduct(@PathParam("productID") int aid){
        List<Bid> bids = em.getAllBidsForProduct(aid); //p.getBids();
        return Response.ok(bids.toString()).build();
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
