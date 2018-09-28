package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class AppUser implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private int id;	
    
    @OneToOne
	@JoinColumn(name="product_catalog_fk")
	private ProductCatalog productCatalog;

    @OneToMany(mappedBy = "buyer")
    List<Bid> bids;

    @Embedded
	private ContactInformation contactInformation;

	private String username;

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