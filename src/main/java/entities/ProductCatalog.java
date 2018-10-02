package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class ProductCatalog  implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue
    private int id;

    @XmlTransient
    @OneToOne(mappedBy ="productCatalog")
    private AppUser seller;
    
    @OneToMany(mappedBy = "productCatalog")
    private List <Product> products = new ArrayList<>();
    
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
