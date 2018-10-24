package services;

import dao.IDao;
import entities.*;
import util.Pair;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class AuctionService implements IAuctionService {

    @Inject
    IDao<Auction, Integer> auctionDao;

    @Inject
    IDao<Bid, Integer> bidDao;

    @Inject
    IDao<Feedback, Integer> feedbackDao;

    @Inject
    IDao<Product, Integer> productDao;

    @Inject
    IDao<User, String> userDao;

    @Override
    public Pair<Bid, Boolean> placeBid(int auctionId, int value) {
        entities.Auction auction = auctionDao.findOrThrow(auctionId);

        // TODO
        // Dette er ikke helt riktig
        // Bid gir egentlig ikke mening uten user, men det legger vi til når vi har innlogging

        Bid bid = new Bid(auction, null, value, new Date());
        auction.getBids().add(bid);
        auctionDao.save(auction);
        bid = bidDao.save(bid);
        return new Pair<>(bid, isHighestBid(auction, bid));
    }

    @Override
    public Feedback placeFeedback(int productId, String content, Double rating, String username) {
        Product product = productDao.findOrThrow(productId);
        User user = userDao.findOrThrow(username);

        Optional<Bid> highestBid = getHighestBid(product.getAuction());
        if (!highestBid.isPresent() || !highestBid.get().getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("User trying to place feedback has not won the auction.");
        }

        Feedback feedback = new Feedback(content, rating, new Date());
        feedbackDao.persist(feedback);
        user.addFeedback(feedback);
        product.addFeedback(feedback);

        feedbackDao.flush();
        updateProductRating(product);
        return feedback;
    }

    @Override
    public Auction publishNewAuction(String picturePath, Description description, String extras, String category, String username) {
        Product product = new Product(true, picturePath, extras, category, description);
        Auction auction = new Auction();
        auction.setProduct(product);

        User user = userDao.findOrThrow(username);
        user.getProductCatalog().addProduct(product);

        productDao.save(product);
        auctionDao.save(auction);

        return auction;
    }

    @Override
    public void deleteProduct(int productId, String username) {
        User user = userDao.findOrThrow(username);
        Product product = productDao.findOrThrow(productId);
        product.removeAuction(product.getAuction());
        productDao.save(product);
    }

    /**
     * Setter rating til riktig verdi
     * 0 om ikke noen rating enda er avgitt
     *
     * @param product - produktet som skal oppdateres
     */
    private void updateProductRating(Product product) {
        List<Feedback> feedbacks = product.getFeedbacks();
        double rating = 0;
        for (int i = 0; i < feedbacks.size(); i++) {
            rating += feedbacks.get(i).getRating();
        }
        rating /= feedbacks.size();
        product.getDescription().setRating(rating);
    }

    /**
     * Finne det høyeste budet
     *
     * @param a Auksjonen
     * @return det høyeste budet eller null om ingen har budd
     */
    private Optional<Bid> getHighestBid(Auction a) {
        if (a.getBids().isEmpty()) {
            return Optional.empty();
        }
        List<Bid> bids = a.getBids();
        Collections.sort(bids);
        return Optional.of(bids.get(bids.size() - 1));

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
