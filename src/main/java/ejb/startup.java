package ejb;

import entities.Bid;
import entities.Feedback;

import java.util.Date;

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

    @PostConstruct
    void init(){
        
        for (int i = 0; i < 10; i++) {
        	 bid = new Bid();
             bid.setValue(i);
             bid.setTime(new Date());	
             bd.persist(bid);
		}
        
        
        feed.setContent("Kult produkt");
        feed.setRating(5.6);
        feed.setTime(new Date());
        bd.persist(feed);
    }
}
