package entities;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.*;


@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    //Create elements ids automatically, incremented 1 by 1
    @Id
    private int id;

    private boolean published;
    private String picturePath;
    private String extras;

    @Enumerated
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productCatalog_fk")
    @XmlTransient
    private ProductCatalog productCatalog;

    @OneToOne
    @MapsId
    @XmlTransient
    private Auction auction;

    @Embedded
    private Description description;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<Feedback> feedbacks;

    public Product() {
    }

    public Product(boolean published, String picturePath, String extras, Category category, Description description) {
        this.published = published;
        this.picturePath = picturePath;
        this.extras = extras;
        this.category = category;
        this.description = description;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProductCatalog(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", published=" + published +
                ", picturePath='" + picturePath + '\'' +
                ", productCatalog=" + productCatalog +
                ", extras=" + extras +
                ", description=" + description +
                '}';
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public void addAuction(Auction auction) {
        this.auction = auction;
        auction.setProduct(this);
    }

    public void removeAuction(Auction auction) {
        this.auction = null;
        auction.setProduct(null);
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
        feedback.setProduct(this);
    }

    public void removeFeedback(Feedback feedback) {
        this.feedbacks.remove(feedback);
        feedback.setProduct(null);

    }

}
