package ejb;

import entities.Bid;
import entities.Feedback;
import entities.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Startup
@Singleton
public class startup {

    //Ejb hive declaration for this object
	@EJB
    private AuctionDao bd;

	@PersistenceUnit(unitName="dat250psql")
    EntityManagerFactory emf;

    private Bid bid = new Bid();
    private Feedback feed = new Feedback();
    private Product product= new Product();

    @PostConstruct
    void init(){

        product.setId(1);
        product.setName("Genesis_Product_1.0");
        product.setPublished(true);
        List testBids = new ArrayList();
        
        for (int i = 0; i < 10; i++) {
        	 bid = new Bid();
             bid.setValue(i);
             bid.setTime(new Date());	
             bd.persist(bid);
             testBids.add(bid);
		}
        product.setBids(testBids);
        bd.persist(product);

        
        feed.setContent("Kult produkt");
        feed.setRating(5.6);
        feed.setTime(new Date());
        bd.persist(feed);
    }
}
