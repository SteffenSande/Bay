package rest;

import dao.IDao;
import dto.Value;
import entities.Auction;
import entities.Bid;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import entities.Feedback;
import entities.Product;
import services.IAuctionService;
import util.Pair;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * Returns a representation with references to all current auctions (ongoing/completed) in the system
 */
@Path("auctions")
@Stateless
public class RestService {

    @Inject
    IDao<Product, Integer> productDao;

    @Inject
    IDao<Auction, Integer> auctionDao;

    @Inject
    IDao<Bid, Integer> bidDao;

    @Context
    UriInfo uri;

    @EJB
    IAuctionService auctionService;

    /**
     * return a representation with references to all current auctions (ongoing/completed) in the system.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAuctions() {
        List<Auction> auctions = auctionDao.getAll();
        return Response.ok().entity(new GenericEntity<List<Auction>>(auctions) {
        }).build();
    }

    /**
     * returns a representation of the auction/product identified by id
     */
    @GET
    @Path("/{auctionId: \\d+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAuction(@PathParam("auctionId") int id) {
        return auctionDao
                .find(id)
                .map(p -> Response.ok().entity(p).build())
                .orElse(auctionNotFound(id));
    }

    @GET
    @Path("/test")
    public Response test() {
        List<Bid> bids = auctionDao.find(3).get().getBids();
        bids.size();
        bids.isEmpty();
        System.out.println(bids);
        return Response.ok(bids.toString()).build();
    }

    /**
     * return a representation with reference to all current bids in the auction identified by id
     */
    @GET
    @Path("/{productID : \\d+}/bids/")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response showBidsOnProduct(@PathParam("productID") int id) {
        return auctionDao.find(id)
                .map(p -> Response.ok().entity(new GenericEntity<List<Bid>>(p.getBids()){}).build())
                .orElse(auctionNotFound(id));
    }

    /**
     * returns a representation of the given bid within the auction identified by "auctionId" and "bidId"
     */
    @GET
    @Path("/{productID : \\d+}/bids/{bidID : \\d+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getSpecificBid(@PathParam("productID") int auctionId, @PathParam("bidID") int bidId) {
        Optional<Auction> auction = auctionDao.find(auctionId);
        if (!auction.isPresent()) {
            return auctionNotFound(auctionId);
        }
        Optional<Bid> bid = auction.get().getBids().stream().filter(b -> b.getId() == bidId).findAny();
        if (!bid.isPresent()) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(String.format("Product %d does not contain a bid with id %d.", auctionId, bidId))
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        return Response.ok().entity(bid.get()).build();
    }

    /**
     * creates a bid with a specified amount in the auction
     * identified by id and returns a representation of the bid. The amount should be contained
     * in the payload of the request (or optionally as a query parameter).
     */

    @POST
    @Path("/{productID : \\d+}/bids")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response bidOnAProduct(@PathParam("productID") int auctionId, Value value) {
        Pair<Bid, Boolean> p = auctionService.placeBid(auctionId, value.get());
        Bid bid = p.fst();
        URI bidUri = UriBuilder.fromUri(uri.getBaseUri()).path("auctions").path(Integer.toString(auctionId)).path("bids").path(Integer.toString(bid.getId())).build();
        return Response.created(bidUri).entity(bid).build();
    }

    /**
     * This is for test purposes only, as it doesn't use AuctionDao. When everyone is comfortable with testing,
     * this method can be removed, though it wont do any harm.
     */
    @GET
    @Path("/testFeedback")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response testFeedback() {
        return Response.ok(new Feedback("Content", 4, new Date())).build();
    }

    private Response auctionNotFound(int id) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(String.format("Auction %d does not exist.", id))
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
