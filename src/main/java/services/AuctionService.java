package services;

import dao.IDao;
import entities.*;
import util.Pair;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.soap.SOAPBinding;
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

    /**
     * Place bid
     */
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

    /**
     * Give product feedback
     */
    @Override
    public Pair<Feedback, Boolean> placeFeedback(int productId, String content, Double rating, int userId) {
        Product product = productDao.find(productId).orElseThrow(NoSuchElementException::new);
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
     * Create new auction
     */
    public Pair<Auction, Boolean> publishAuction(String picturePath, Description description, String extras, Category category, int userId) {
        User user = userDao.find(userId).orElseThrow(NoSuchElementException::new);
        Product product = new Product();
        product.setPicturePath(picturePath);
        product.setDescription(description);
        product.setExtras(extras);
        product.setCategory(category);
        product.setPublished(true);

        //Assume that every user has a product catalog by default even though they don't have any products (yet).
        user.getProductCatalog().addProduct(product);

        //New auction to link the product to
        Auction auction = new Auction();
        auction.setProduct(product);

        productDao.persist(product);
        auctionDao.persist(auction);

        return new Pair<>(auction, true);
    }

    /**
     * Register new user
     */
    public Pair<User, Boolean> registerUser(String username, String name, String phone, String email, String street, String city, int zip) {

        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setName(name);
        contactInformation.setPhone(phone);
        contactInformation.setEmail(email);

        Address address = new Address();
        address.setCity(city);
        address.setStreet(street);
        address.setZip(zip);
        contactInformation.setAddress(address);

        User user = new User();
        user.setUsername(username);
        user.setContactInformation(contactInformation);

        ProductCatalog productCatalog = new ProductCatalog();
        user.setProductCatalog(productCatalog);
        return new Pair<>(user, true);
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
