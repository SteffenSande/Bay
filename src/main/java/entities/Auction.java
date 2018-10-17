package entities;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Auction {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "auction", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @XmlElement(name = "bids")
    @XmlInverseReference(mappedBy = "auction")
    private List<Bid> bids;

    @OneToOne(mappedBy = "auction", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @XmlTransient
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void addBid(Bid bid) {
        if (this.bids == null)
            this.bids = new ArrayList<>();
        this.bids.add(bid);
        bid.setAuction(this);
    }

    public void removeBid(Bid bid) {
        this.bids.remove(bid);
        bid.setAuction(null);
    }

    public void addProduct(Product product) {
        this.product = product;
        product.setAuction(this);
    }

    public void removeProduct(Product product) {
        this.product = null;
        product.setAuction(null);
    }


    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id + "}";
    }
}
