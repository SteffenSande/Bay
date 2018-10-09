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
    IDao<User, Integer> userDao;

    @Override
    public Pair<Bid, Boolean> placeBid(int auctionId, int value) {
        entities.Auction auction = auctionDao.find(auctionId).orElseThrow(NoSuchElementException::new);

        //Dette er ikke helt riktig
        //Bid gir egentlig ikke mening uten user, men det legger vi til når vi har innlogging

        Bid bid = new Bid(auction, null, value, new Date());
        auction.getBids().add(bid);
        auctionDao.save(auction);
        bid = bidDao.save(bid);
        return new Pair<>(bid, isHighestBid(auction, bid));
    }

    @Override
    public Pair<Feedback, Boolean> placeFeedback(int produktId, String content, Double rating, int userId) {
        Product product = productDao.find(produktId).orElseThrow(NoSuchElementException::new);
        User user = userDao.find(userId).orElseThrow(NoSuchElementException::new);

        Bid highestBid = this.getHighestBid(product.getAuction());
        if (user.getBids().contains(highestBid)) {
            Feedback feedback = new Feedback();
            //persist
            feedbackDao.persist(feedback);
            user.addFeedback(feedback);
            product.addFeedback(feedback);

            //Update object
            feedback.setTime(new Date());
            feedback.setContent(content);
            feedback.setRating(rating);
            feedbackDao.flush();
            this.updateProductRating(product);
            return new Pair<>(feedback, true);
        }else {
            return new Pair<>(null, false);
        }
    }


    /**
     * Setter rating til riktig verdi
     * 0 om ikke noen rating enda er avgitt
     * @param product - produktet som skal oppdateres
     */

    private void updateProductRating(Product product){
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
     * @param a Auksjonen
     * @return det høyeste budet eller null om ingen har budd
     */
    private Bid getHighestBid(Auction a){
        if (a.getBids().isEmpty()) {
            return null;
        }
        List<Bid> bids = a.getBids();
        Collections.sort(bids);
        return bids.get(bids.size()-1);

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
