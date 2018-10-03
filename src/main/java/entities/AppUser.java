package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class AppUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "appUser")
    private List<Bid> bids;

    @OneToOne()
    @JoinColumn(name = "productCatalog_fk")
    private ProductCatalog productCatalog;


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

}
