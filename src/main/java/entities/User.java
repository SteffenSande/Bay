package entities;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Appuser")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

    @XmlTransient
    @OneToMany(mappedBy = "user")
    private List<Bid> bids;


    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    @XmlInverseReference(mappedBy = "seller")
    private ProductCatalog productCatalog;

/*
    @XmlTransient
    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;
*/



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

    public void addProductCatalog(ProductCatalog pc){
        this.productCatalog = pc;
        pc.setSeller(this);
    }

    public void removeProductCatalog(){
        this.productCatalog.setSeller(null);
        this.productCatalog = null;
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
