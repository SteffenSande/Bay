package services;

import entities.Bid;
import util.Pair;

/**
 * @return (the created bid, is this bid the highest)
 */
public interface IAuctionService {
    Pair<Bid, Boolean> placeBid(int auctionId, int value);
}
