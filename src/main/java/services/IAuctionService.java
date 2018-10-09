package services;

import entities.*;
import util.Pair;

/**
 * @return (the created bid, is this bid the highest)
 */
public interface IAuctionService {
    Pair<Bid, Boolean> placeBid(int auctionId, int value);
    Pair<Feedback, Boolean> placeFeedback(int productId, String content, Double rating, int userId);
    Pair<Auction, Boolean> publishAuction(String picturePath, Description description, String extras, Category category, int userId);
    Pair<User, Boolean> registerUser(String username, String name, String phone, String email, String street, String city, int zip);
}

