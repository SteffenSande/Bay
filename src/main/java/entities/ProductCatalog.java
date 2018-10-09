package entities;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class ProductCatalog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;


    @OneToOne
    @MapsId
    @XmlInverseReference(mappedBy = "productCatalog")
    private User seller;

    @OneToMany(mappedBy = "productCatalog")
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
