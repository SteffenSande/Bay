package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


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

    @XmlTransient
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productCatalog_fk")
    private ProductCatalog productCatalog;

    private String extras;

    @XmlTransient
    @OneToOne
    private Auction auction;

    @Embedded
    private Description description;

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

    //Make some entityy relations between this object and these variable
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
}
