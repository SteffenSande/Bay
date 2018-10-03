package entities;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

@Entity
public class ProductCatalog implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "seller_fk")
    private AppUser seller;


    @OneToMany(mappedBy = "productCatalog")
    @XmlElement
    @XmlInverseReference(mappedBy = "product")

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public AppUser getSeller() {
        return seller;
    }

    public void setSeller(AppUser seller) {
        this.seller = seller;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
