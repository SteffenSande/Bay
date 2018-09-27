package entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NamedQuery(name="Bid.findAll", query="SELECT b FROM Bid b")
public class Bid implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private int id;
    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private double value;

    //Used in the RestService.java
    public static final String FIND_ALL = "Bid.findAll";


    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

	
	
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
