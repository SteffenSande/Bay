package entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Address  implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private int id;

    private String street;
	private String city;
    private int zip;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
