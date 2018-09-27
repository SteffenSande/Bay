package ejb;

import entities.Bid;
import entities.Feedback;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
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
        bid.setValue(1234);
        feed.setContent("Kult produkt");
        feed.setRating(5.6);


        bd.persist(bid);
        bd.persist(feed);
    }
}
