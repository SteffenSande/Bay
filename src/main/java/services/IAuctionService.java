package services;

import com.sun.tools.javac.util.Pair;
import entities.Bid;

/**
 * @return (the created bid, is this bid the highest)
 */
public interface IAuctionService {
    Pair<Bid, Boolean> placeBid(int auctionId, int value);
}
