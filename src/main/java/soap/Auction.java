package soap;

import dao.AuctionDao;
import dao.BidDao;
import dao.IDao;
import entities.Bid;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@WebService
public class Auction {

    @Inject
    IDao<entities.Auction, Integer> auctionDao;

    @Inject
    IDao<Bid, Integer> bidDao;

    @WebMethod
    public List<entities.Auction> getAuctions() {
        return auctionDao.getAll();
    }

    @WebMethod
    public entities.Auction getAuction(int id) {
        return auctionDao.find(id).orElseThrow(NoSuchElementException::new);
    }

    @WebMethod
    @Transactional
    public String placeBid(int auctionId, int value) {
        entities.Auction auction = auctionDao.find(auctionId).orElseThrow(NoSuchElementException::new);

        boolean currentlyHighestBid = auction
                .getBids()
                .stream()
                .map(Bid::getValue)
                .allMatch(v -> v < value);

        Bid bid = new Bid(auction, null, value, new Date());
        auction.getBids().add(bid);
        auctionDao.save(auction);
        bidDao.save(bid);
        if (currentlyHighestBid) {
            return "This is currently the highest bid";
        } else {
            return "Someone has bid higher";
        }
    }
}
