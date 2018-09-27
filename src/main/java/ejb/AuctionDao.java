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

    static final String DAT250_PSQL_UNIT_NAME = "dat250psql";

	@PersistenceContext(unitName=DAT250_PSQL_UNIT_NAME)
    private EntityManager em;

    public void persist(Bid bid) {
        em.persist(bid);
    }
    
    public void persist(Feedback feed) {
		em.persist(feed);
	}

    public void persist(Product product) {
        em.persist(product);
    }

	public List<Bid> getAllBids() {
    	Query query = em.createQuery("SELECT bid FROM Bid bid");
        List<Bid> bids = query.getResultList();
        return bids;
    }

    public List<Bid> getAllBidsForProduct(int id) {
        List<Bid> bids = em.createQuery("SELECT p FROM Product p WHERE p.id = :id")
                .setParameter("id", id)
                .getResultList();
        return bids;
    }

    //Get all products
    public List<Product> getAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        List<Product> products = new ArrayList<>();
        products = query.getResultList();
        return products;
    }

    public Product getProductByID(int id) {
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.id = :id").setParameter("id", id);
        return (Product) query.getSingleResult();
    }

}