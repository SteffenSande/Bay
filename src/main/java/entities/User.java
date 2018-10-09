package entities;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Appuser")
@XmlRootElement
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
    private List<Bid> bids;

    @OneToOne(mappedBy = "seller")
    private ProductCatalog productCatalog;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
    private List<Feedback> feedbacks;


    @Embedded
    private ContactInformation contactInformation;

    private String username;


    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }


    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addBid(Bid bid){
        if (this.bids == null)
            this.bids = new ArrayList<>();
        this.bids.add(bid);
        bid.setUser(this);
    }

    public void removeBid(Bid bid){
        this.bids.remove(bid);
        bid.setUser(null);
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void addFeedback(Feedback feedback){
        if (this.getFeedbacks() == null)
            this.feedbacks = new ArrayList<>();
        this.feedbacks.add(feedback);
        feedback.setUser(this);
    }

    public void removeFeedback(Feedback feedback){
        this.feedbacks.remove(feedback);
        feedback.setUser(null);

    }

    public void addProductCatalog (ProductCatalog productCatalog){
        productCatalog.setSeller(this);
        this.productCatalog = productCatalog;
    }

    public void removeProductCatalog(ProductCatalog productCatalog){
        this.productCatalog = null;
        productCatalog.setSeller(null);
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", bids=" + bids +
                ", productCatalog=" + productCatalog +
                ", contactInformation=" + contactInformation +
                ", username='" + username + '\'' +
                '}';
    }

}
