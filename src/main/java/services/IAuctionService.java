package services;

import entities.*;
import util.Pair;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface IAuctionService {

    /**
     * Places a bid on an auction
     *
     * @return Pair: (the created bid, is this bid the highest?)
     * @throws NoSuchElementException if no such auction exist
     */
    Pair<Bid, Boolean> placeBid(int auctionId, User user, int value);

    Optional<Bid> getHighestBid(Auction auction);

    Feedback placeFeedback(int productId, String content, Double rating, String username);

    void deleteProduct(int pid, String username);

    Auction publishNewAuction(String picturePath, Description description, String extras, String category, String username);
}

