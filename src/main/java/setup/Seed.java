package setup;

import dao.IDao;
import entities.noe;
import entities.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;

//@Startup
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
    IDao<ProductCatalog, Integer> productCatalogDao;
    @Inject
    IDao<User, Integer> userDao;

    @PersistenceContext(unitName = Configuration.CURRENT_PERSISTENCE_UNIT)
    EntityManager em;

    private Feedback feedback = new Feedback();
    private Product product = new Product();
    private Auction auction = new Auction();
    private ProductCatalog productCatalog = new ProductCatalog();
    private User seller = new User();
    private noe ne= new noe();

    @PostConstruct
    void init() {
        productDao.persist(product);
        auctionDao.persist(auction);
        userDao.persist(seller);
        seller.setUsername("Bob");
        seller.addProductCatalog(productCatalog);
        productDao.save(product);
        productDao.flush();
        product.setFeedback(feedback);
        feedbackDao.persist(feedback);
        productCatalogDao.persist(productCatalog);

        auction.setProduct(product);
        product.setAuction(auction);

        auction.setBids(new ArrayList<>());

        product.setPublished(true);
        product.setPicturePath("Some url");

        for (int i = 0; i < 4; i++) {
            Bid bid = new Bid();
            bid.setAuction(auction);
            auction.getBids().add(bid);
            bid.setValue(i);
            bid.setTime(new Date());
            bidDao.persist(bid);
        }

        feedback.setContent("Kult produkt");
        feedback.setRating(5.6);
        feedback.setTime(new Date());

        User u = new User();
        userDao.persist(u);
        u.setUsername("MyName");
        u.setBids(new ArrayList<>());

        Bid b = new Bid();
        b.setUser(u);
        b.setAuction(auction);
        b.setValue(250);
        b.setTime(new Date());
        bidDao.persist(b);

        bidDao.flush();
        userDao.flush();
        auctionDao.flush();

        System.out.println("Found bids:");
        System.out.println(u.getBids());
        System.out.println(auction.getBids());

        em.createQuery("SELECT b FROM Bid b where b.user = :user")
                .setParameter("user", u)
                .getResultList()
                .stream()
                .forEach(System.out::println);

        ne.setHei("hei");
        em.persist(ne);

    }
}
