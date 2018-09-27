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
        return Response.ok(p.toString()).build();
    }

    /** return a representation with reference to all current bids in the auction identified by id */
    @GET
    @Path("/{productID : \\d+}/bids/")
    public Response bi(@PathParam("productID") int id){
        Product p = auctionDao.getProductByID(id);
        List<Bid> bids = p.getBids();
        return Response.ok(bids.toString()).build();
    }
}
