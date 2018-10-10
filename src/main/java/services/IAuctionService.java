package services;

import entities.Bid;
import entities.Feedback;
import entities.Product;
import util.Pair;

import java.util.NoSuchElementException;

public interface IAuctionService {

    /**
     * Places a bid on an auction
     *
     * @return Pair: (the created bid, is this bid the highest?)
     * @throws NoSuchElementException if no such auction exist
     */
    Pair<Bid, Boolean> placeBid(int auctionId, int value);


    /**
     * Places a feedback on an auction
     *
     * @return
     */
    Pair<Feedback, Boolean> placeFeedback(int produktId, String content, Double rating, int userId);
}

