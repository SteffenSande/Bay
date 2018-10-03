package setup;

import dao.AuctionDao;
import dao.IDao;
import entities.Bid;
import entities.Feedback;
import entities.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;

@Startup
@Singleton
public class Seed {

    @Inject
    IDao<Bid, Integer> bidDao;

    @Inject
    IDao<Product, Integer> productDao;

    @Inject
    IDao<Feedback, Integer> feedbackDao;

    private Feedback feedback = new Feedback();
    private Product product = new Product();

    @PostConstruct
    void init() {
        System.out.println("1234");
        System.out.println(bidDao);

        productDao.persist(product);
        feedbackDao.persist(feedback);

        product.setPublished(true);
        product.setPicturePath("Some url");
        product.setProductCatalog(null);
        product.setBids(new ArrayList<>());

        for (int i = 0; i < 4; i++) {
            Bid bid = new Bid();
            product.getBids().add(bid);
            bidDao.persist(bid);
            bid.setProduct(product);
            bid.setValue(i);
            bid.setTime(new Date());
        }

        feedback.setContent("Kult produkt");
        feedback.setRating(5.6);
        feedback.setTime(new Date());
    }
}
