package rest;

import ejb.*;
import entities.Bid;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import entities.Product;

import java.net.URI;
import java.util.Date;
import java.util.List;


/** Returns a representation with references to all current auctions (ongoing/completed) in the system */
@Path("auctions")
@Stateless
public class RestService {

    //Ejb hive declaration for this object
    @EJB
    private BidDao bidDao;
    @EJB
    private AppUserDao appUserDao;
    @EJB
    private AppUserDao userDao;
    @EJB
    private ProductDao productDao;
    @EJB
    private FeatureDao featureDao;
    @EJB
    private ProductCatalogDao productCatalogDao;


    /** return a representation with references to all current auctions (ongoing/completed) in the system. */
    @GET
    @Produces({MediaType.TEXT_XML,MediaType.APPLICATION_JSON})
    public Response getAllProducts(){
	    List <Product> products = productDao.getList();
	    return Response.ok().entity(products).build();
    }

    /** returns a representation of the auction/product identified by id */
    @GET
    @Path("/{productID: \\d+}")
    @Produces({MediaType.TEXT_XML,MediaType.APPLICATION_JSON})
    public Response getProductsByID(@PathParam("productID") int id){
        Product p = productDao.getItemByPk(id);
        return Response.ok().entity(p).build();
    }

    /** return a representation with reference to all current bids in the auction identified by id */
    @GET
    @Path("/{productID : \\d+}/bids/")
    @Produces({MediaType.TEXT_XML,MediaType.APPLICATION_JSON})
    public Response showBidsOnProduct(@PathParam("productID") int aid){
        List<Bid> bids = bidDao.getAllBidsForProduct(aid); //p.getBids();
        GenericEntity<List<Bid>> genericList = new GenericEntity<List<Bid>>(bids) {};
        return Response.ok().entity(genericList).build();
    }

    /** returns a representation of the given bid within the auction identified by "aid" and "bidId" */
    @GET
    @Path("/{productID : \\d+}/bids/{bidID : \\d+}")
    @Produces({MediaType.TEXT_XML,MediaType.APPLICATION_JSON})
    public Response getSpecificBid(@PathParam("productID") int aid, @PathParam("bidID") int bidID){
        Bid bid = bidDao.getItemByPk(bidID);
        return Response.ok().entity(bid).build();
    }

    /** creates a bid with a specified amount in the auction
    identified by id and returns a representation of the bid. The amount should be contained
    in the payload of the request (or optionally as a query parameter).*/

    @POST
    @Path("/{productID : \\d+}/bids/{bidAmount : \\d+}")
    @Produces({MediaType.TEXT_XML,MediaType.APPLICATION_JSON})
    public Response bidOnAProduct(@PathParam("productID") int aid, @PathParam("bidAmount") int bidAmount){
        Product p = productDao.getItemByPk(aid);
        Bid bid = new Bid();
        bid.setValue(bidAmount);
        bid.setTime(new Date());
        bidDao.persist(bid);
        p.setBid(bid);
        URI uri = UriBuilder.fromUri("http://localhost:8080/Bay/rest/auctions").path(Integer.toString(aid)).path(Integer.toString(bidAmount)).build();
        return Response.created(uri).entity(bid).build();
    }
}
