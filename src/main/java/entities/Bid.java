package entities;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement
@NamedQuery(name="Bid.findAll", query="SELECT b FROM Bid b")
public class Bid implements Serializable, Comparable <Bid>
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private int id;
    
    
    public int getId() {
		return id;
	}

	@ManyToOne
    private Product product;

	private double value;

    //Used in the RestService.java
    public static final String FIND_ALL = "Bid.findAll";


    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setId(int id) {
        this.id = id;
    }

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
    
    @Override
    public String toString() {
    	return "Bid id " + id + " value: " + value + " time " + time.toString() + "\n";
    }

	@Override
	public int compareTo(Bid o) {
		return this.id - o.id;
	}
}
