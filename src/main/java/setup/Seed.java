package setup;

import dao.IDao;
import entities.*;
import sun.security.krb5.internal.crypto.Des;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Startup
@Singleton
public class Seed {

    @Inject
    IDao<Bid, Integer> bidDao;

    @Inject
    IDao<Product, Integer> productDao;

    @Inject
    IDao<Auction, Integer> auctionDao;

    @Inject
    IDao<Feedback, Integer> feedbackDao;

    @Inject
    IDao<User, String> userDao;

    @Inject
    IDao<ProductCatalog, Integer> productCatalogDao;


    @PersistenceContext(unitName = Configuration.CURRENT_PERSISTENCE_UNIT)
    EntityManager em;


    @PostConstruct
    void init() {

        User bidder = createUser("bob");

        bidder.addProductCatalog(createProductCatalog());

        User seller = createUser("alice");

        seller.addProductCatalog(createProductCatalog());


        Auction auction = createAuction();

        Auction auction2 = createAuction();
        Auction auction3 = createAuction();
        Auction auction4 = createAuction();
        Auction auction5 = createAuction();

        Product product = createProduct();
        Product product2 = createProduct();
        Product product3 = createProduct();
        Product product4 = createProduct();
        Product product5 = createProduct();

        auction.addProduct(product);
        auction2.addProduct(product2);
        auction3.addProduct(product3);
        auction4.addProduct(product4);
        auction5.addProduct(product5);

        List<Bid> bids = createBids();


        for (int i = 0; i < bids.size(); i++) {
            bidder.addBid(bids.get(i));
            auction.addBid(bids.get(i));
        }

        Feedback feed = createFeedback();
        bidder.addFeedback(feed);
        product.addFeedback(feed);

        double rating = 0;
        for (int i = 0; i < product.getFeedbacks().size(); i++) {
            rating += product.getFeedbacks().get(i).getRating();
        }
        rating /= product.getFeedbacks().size();
        product.setDescription(createDescription("This is the sporty description", rating));
        product.setCategory("Sport");

        product2.setDescription(createDescription("This is some electronics description ", rating));
        product2.setCategory("Electronics");

        product3.setDescription(createDescription("This is the second sporty description", rating));
        product3.setCategory("Sport");

        product4.setDescription(createDescription("This is another electronics description ", rating));
        product4.setCategory("Electronics");

        product5.setDescription(createDescription("This is the third electronics description ", rating));
        product5.setCategory("Electronics");
    }


    ProductCatalog createProductCatalog() {
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalogDao.persist(productCatalog);
        return productCatalog;
    }


    Description createDescription(String content, double rating) {
        Description description = new Description();
        description.setRating(rating);
        description.setEndDate(new Date());
        description.setTitle("Kult produkt");
        description.setContent(content);
        return description;
    }

    Feedback createFeedback() {
        Feedback feedback = new Feedback();
        feedbackDao.persist(feedback);
        feedback.setContent("Superfeedback");
        feedback.setRating(5);
        feedback.setTime(new Date());
        return feedback;
    }


    Auction createAuction() {
        Auction auction = new Auction();
        auctionDao.persist(auction);
        return auction;
    }

    Product createProduct() {
        Product product = new Product();
        productDao.persist(product);
        product.setPublished(true);
        product.setPicturePath("Some url");
        product.setCategory("Sport");
        return product;
    }

    List<Bid> createBids() {
        List<Bid> bids = new ArrayList<>();
        //for (int i = 0; i < 4; i++) {
            Bid bid = new Bid();
            bid.setValue(1);
            bid.setTime(new Date());
            bidDao.persist(bid);
            bids.add(bid);
        //}
        return bids;
    }


    User createUser(String name) {
        User user = new User((name + "@epost.no"), name, (name.charAt(0)+"").toUpperCase() + name.substring(1) + "123");
        userDao.persist(user);
        user.setContactInformation(createContactinformation(name));
        return user;
    }


    ContactInformation createContactinformation(String name) {
        ContactInformation ci = new ContactInformation();
        ci.setPhone("12345678");
        ci.setAddress(createAdress());
        return ci;
    }

    Address createAdress() {
        Address ad = new Address();
        ad.setZip(1234);
        ad.setStreet("Ene gata uten regn");
        ad.setCity("Bergen");
        return ad;
    }
}
