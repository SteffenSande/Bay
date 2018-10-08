package entities;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.io.Serializable;
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
    @GeneratedValue
    private int id;

    private boolean published;
    private String picturePath;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productCatalog_fk")
    @XmlElement
    @XmlInverseReference(mappedBy = "productCatalog")
    private ProductCatalog productCatalog;

    @XmlTransient
    @OneToMany(mappedBy = "product")
    private List<PracticalInfo> features;

    @XmlTransient
    @OneToOne
    private Auction auction;

    @Embedded
    private Description description;

    @OneToOne(mappedBy = "product")
    private Feedback feedback;


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

    public List<PracticalInfo> getFeatures() {
        return features;
    }

    public void setFeatures(List<PracticalInfo> features) {
        this.features = features;
        for (int i = 0; i < features.size(); i++) {
            this.features.get(i).setProduct(this);
        }
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public void addFeedback(Feedback feedback) {
        this.feedback.addProduct(this);
    }


    public ProductCatalog getProductCatalog() {
        return productCatalog;
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
                ", features=" + features +
                ", description=" + description +
                '}';
    }
}
