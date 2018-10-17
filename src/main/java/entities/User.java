package entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Appuser")
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String username;

    @Embedded
    private ContactInformation contactInformation;

    @OneToOne(mappedBy = "seller")
    @XmlTransient
    private ProductCatalog productCatalog;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Bid> bids;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Feedback> feedbacks;

    public User() {
    }

    public User(String username, ContactInformation contactInformation, ProductCatalog productCatalog) {
        this.username = username;
        this.contactInformation = contactInformation;
        this.productCatalog = productCatalog;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addBid(Bid bid) {
        if (this.bids == null)
            this.bids = new ArrayList<>();
        this.bids.add(bid);
        bid.setUser(this);
    }

    public void removeBid(Bid bid) {
        this.bids.remove(bid);
        bid.setUser(null);
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void addFeedback(Feedback feedback) {
        if (this.getFeedbacks() == null)
            this.feedbacks = new ArrayList<>();
        this.feedbacks.add(feedback);
        feedback.setUser(this);
    }

    public void removeFeedback(Feedback feedback) {
        this.feedbacks.remove(feedback);
        feedback.setUser(null);
    }

    public void addProductCatalog(ProductCatalog productCatalog) {
        productCatalog.setSeller(this);
        this.productCatalog = productCatalog;
    }

    public void removeProductCatalog(ProductCatalog productCatalog) {
        this.productCatalog = null;
        productCatalog.setSeller(null);
    }


    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", productCatalog=" + productCatalog +
                ", contactInformation=" + contactInformation +
                ", bids=" + bids +
                '}';
    }

}
