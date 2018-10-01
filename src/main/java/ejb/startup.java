package ejb;

import entities.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class startup {

    //Ejb hive declaration for to
    // his object
    /*
	@EJB
    private BidDao bidDao;
	@EJB
    private AppUserDao appUserDao;
    @EJB
    private AppUserDao userDao;
    @EJB
    private ProductDao productDao;
    @EJB
    private FeatureDao featureDao;
    @EJB
    private ProductCatalogDao productCatalogDao;



    private Bid bid = new Bid();
    private Feedback feed = new Feedback();
    private Product product= new Product();
    private AppUser appuser = new AppUser();
    private ProductCatalog productCatalog = new ProductCatalog();

    @PostConstruct
    void init(){

        // Set user
        appuser.setUsername("Bobkåren");

        Address address = new Address();
        address.setCity("Bergen");
        address.setStreet("Sandviken");
        address.setZip(5033);


        ContactInformation ci = new ContactInformation();
        ci.setAddress(address);
        ci.setEmail("BobkåreSinEPost@epost.no");
        ci.setName("Bob Kåre");
        ci.setPhone("+47 95 19 55 55");

        appuser.setContactInformation(ci);

        product.setId(1);
        product.setPublished(true);
        product.setPicturePath("Some url");
        product.setProductCatalog(null);

        Date idag = new Date();

        Description description = new Description();
        description.setTitle("YoYoYOyoooo");
        description.setRating(4);
        description.setEndDate(idag);


        List <Bid> testBids = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
        	 bid = new Bid();
             bid.setValue(i);
             bid.setTime(new Date());
             bid.setProduct(product);
             testBids.add(bid);
		}

		product.setBids(testBids);
        product.setDescription(description);

        List<Product> products = new ArrayList<>();
        products.add(product);

        productCatalog.setProducts(products);

        appuser.setProductCatalog(productCatalog);

        appUserDao.persist(appuser);

        */
    @EJB
    BidDao bidDao;

    private Bid bid = new Bid();

    @PostConstruct
    void init(){
        bid.setValue(1);
        bid.setTime(new Date());
        bidDao.persist(bid);
    }
}
