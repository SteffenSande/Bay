package services;

import com.sun.tools.javac.util.Pair;
import dao.IDao;
import entities.Auction;
import entities.Bid;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class AuctionService implements IAuctionService {

    @Inject
    IDao<Auction, Integer> auctionDao;

    @Inject
    IDao<Bid, Integer> bidDao;

    @Override
    public Pair<Bid, Boolean> placeBid(int auctionId, int value) {
        entities.Auction auction = auctionDao.find(auctionId).orElseThrow(NoSuchElementException::new);
        Bid bid = new Bid(auction, null, value, new Date());
        auction.getBids().add(bid);
        auctionDao.save(auction);
        bid = bidDao.save(bid);
        return new Pair<>(bid, isHighestBid(auction, bid));
    }

    private boolean isHighestBid(Auction a, Bid bid) {
        if (a.getBids().isEmpty()) {
            return true;
        }
        List<Bid> bids = new ArrayList<>(a.getBids());
        Collections.sort(bids);
        return bids.get(bids.size() - 1).equals(bid);
    }
}
