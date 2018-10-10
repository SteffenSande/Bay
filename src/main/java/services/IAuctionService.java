package services;

import entities.*;
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

    Feedback placeFeedback(int productId, String content, Double rating, String username);

    void deleteProduct(int pid, String username);

    Auction publishNewAuction(String picturePath, Description description, String extras, Category category, String username);
}

