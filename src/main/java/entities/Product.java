package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Product implements Serializable
{
    private static final long serialVersionUID = 1L;
	 //Create elements ids automatically, incremented 1 by 1
    @Id
    @GeneratedValue
    private int id;
    
    private boolean published;
    private String picturePath;
    private String name;
    
    
    
    //Make some entityy relations between this object and these variables
    private User seller;
    private List <Bid> bids;
   

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    

}
