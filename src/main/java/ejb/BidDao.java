package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Bid;



/**
 * Data Access Object connecting the Database with the business logic
 */

@Stateless
public class BidDao {
    // Injected database connection:
	@PersistenceContext(unitName="Bay")
    private EntityManager em;

    // Stores a new tweet:
    public void persist(Bid bid) {
        em.persist(bid);
    }

    // Retrieves all the tweets:
    @SuppressWarnings("unchecked")
	public List<Bid> getAllBids() {
        
    	Query query = em.createQuery("SELECT bid FROM Bid bid");
        
        List<Bid> tweets = new ArrayList<>();
        tweets = query.getResultList();
       
        return tweets;
    }
}