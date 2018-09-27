package ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.Bid;
import entities.Feedback;
import entities.Product;


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
        List<Bid> bids = new ArrayList<>();
        bids = query.getResultList();
        return bids;
    }

    //Get all bids for specific product by Id
    public List<Bid> getAllBidsForProduct(int i) {
        Query query = em.createQuery("SELECT p FROM Product p WHERE Id=i");
        List<Bid> bids = new ArrayList<>();
        bids = query.getResultList();
        return bids;
    }


    public List<Product> getAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        List<Product> products = new ArrayList<>();
        products = query.getResultList();
        return products;
    }

    public Product getProductByID(int i) {
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.id = i");
        return (Product) query.getSingleResult();
    }
}