package ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.QueryParam;

import com.sun.mail.iap.Response;

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

    public void persist(Product product) {
        em.persist(product);
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


    //Get all products
    public List<Product> getAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        List<Product> products = new ArrayList<>();
        products = query.getResultList();
        return products;
    }

    //Get all bids for specific product by Id
    public List<Bid> getAllBidsForProduct(int id) {
    	Product p = em.find(Product.class, id);
    	List <Bid> bids = p.getBids();
    	return bids;
    }

    //Get specific bids for specific product by Id
    public Bid getSpecificBidOnProduct(int aid, int bidId) {
    	Product p = em.find(Product.class, aid);
        Bid b = p.getBidById(bidId);
        return p.getBidById(bidId);
    }



    public Product getProductByID(int i) {
//      Query query = em.createQuery("SELECT p FROM Product p WHERE p.id =: id").setParameter("id", i);
//      return (Product) query.getSingleResult();
    	return em.find(Product.class, i);
  }
}