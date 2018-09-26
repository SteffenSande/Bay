package entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AppUser implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private int id;	
    
    @OneToOne
	@JoinColumn(name= "productcatalog_fk")
	private ProductCatalog productCatalog;

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
