package services;

import entities.Bid;
import entities.Feedback;
import entities.Product;
import util.Pair;

/**
 * @return (the created bid, is this bid the highest)
 */
public interface IAuctionService {
    Pair<Bid, Boolean> placeBid(int auctionId, int value);
    Pair<Feedback, Boolean> placeFeedback(int produktId, String content, Double rating, int userId);
}

