package ejb;

import entities.Bid;
import entities.Feedback;
import entities.Product;
import org.eclipse.persistence.jaxb.JAXBContextFactory;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.xmlmodel.ObjectFactory;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.xml.bind.*;

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

        //product.setId(1);
        product.setName("Genesis_Product_1.0");
        product.setPublished(true);
        product.setPicturePath("Some url");
        product.setProductCatalog(null);


        List <Bid> testBids = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
        	 bid = new Bid();
             bid.setValue(i);
             bid.setTime(new Date());
             bid.setProduct(product);
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

    public static void main(String[] args) throws Exception {
        Feedback f = new Feedback();
        f.setContent("content");
        f.setRating(4);
        f.setTime(new Date());



        Map<String, Object> properties = new HashMap<>(1);
        properties.put(MarshallerProperties.JSON_WRAPPER_AS_ARRAY_NAME, true);
        JAXBContext jc = JAXBContextFactory.createContext(new Class[]{Feedback.class, ObjectFactory.class}, properties);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(MarshallerProperties.JSON_WRAPPER_AS_ARRAY_NAME, true);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Output XML
        marshaller.marshal(f, System.out);

        // Output JSON
        marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
        marshaller.marshal(f, System.out);
    }
}
