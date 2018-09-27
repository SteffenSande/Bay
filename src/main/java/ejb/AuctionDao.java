package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Bid;
import entities.Feedback;



/**
 * Data Access Object connecting the Database with the business logic
 */

@Stateless
public class AuctionDao {
    // Injected database connection:
	@PersistenceContext(unitName="dat250psql")
    private EntityManager em;

    public void persist(Bid bid) {
        em.persist(bid);
    }
    
    public void persist(Feedback feed) {
		em.persist(feed);
	}
    
    
    @SuppressWarnings("unchecked")
	public List<Bid> getAllBids() {
        
    	Query query = em.createQuery("SELECT bid FROM Bid bid");
        List<Bid> tweets = new ArrayList<>();
        tweets = query.getResultList();
       
        return tweets;
    }

	
}