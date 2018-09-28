package entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Feature implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;
    
    private String feature;

    @ManyToOne
	@JoinColumn(name = "product_fk")
    private Product product;


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    
    
    
}
