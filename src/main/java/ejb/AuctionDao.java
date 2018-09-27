package ejb;

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

    public void persist(Product product) {
        em.persist(product);
    }

    public void persist(Feedback feed) {
		em.persist(feed);
	}

	public List<Bid> getAllBids() {
    	Query query = em.createQuery("SELECT bid FROM Bid bid");
        List<Bid> bids = query.getResultList();
        return bids;
    }

    //Get all products
    public List<Product> getAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        List<Product> products = query.getResultList();
        return products;
    }

    //Get all bids for specific product by Id
    public List<Bid> getAllBidsForProduct(int id) {
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class).setParameter("id", id);
        List<Bid> bids = query.getResultList();
        return bids;
    }

    //Get specific bids for specific product by Id
    public Bid getSpecificBidOnProduct(int id) {
        return em.createQuery("SELECT b FROM Bid b WHERE b.id = :id", Bid.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Product getProductByID(int i) {
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.id = :id").setParameter("id", i);
        return (Product) query.getSingleResult();
    }
}