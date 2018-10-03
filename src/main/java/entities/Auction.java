package entities;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@XmlRootElement
public class Auction {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "auction", fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
    private List<Bid> bids;

    @OneToOne(mappedBy = "auction", fetch = FetchType.EAGER, cascade = { CascadeType.ALL})
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
}
