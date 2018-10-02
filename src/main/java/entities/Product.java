package entities;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.*;


@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Serializable
{
    private static final long serialVersionUID = 1L;
	 //Create elements ids automatically, incremented 1 by 1
    @Id
    @GeneratedValue
    private int id;

    private boolean published;
	private String picturePath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productCatalog_fk")
    private ProductCatalog productCatalog;



    @OneToMany(cascade = CascadeType.ALL)
	@XmlElement
	@XmlInverseReference(mappedBy = "product")
	private List <Bid> bids;

    @XmlTransient
	@OneToMany(mappedBy = "product")
	private List<Feature> features;


	@Embedded
	private Description description;


	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
		for (int i = 0; i < features.size(); i++) {
			this.features.get(i).setProduct(this);
		}
	}

	//Make some entityy relations between this object and these variable
    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

    public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	@XmlTransient
	public List<Bid> getBids() {
		return bids;
	}

	public Bid getBidById(int bidID) {
		List<Bid> bids = this.getBids();
		for (int i = 0; i < bids.size(); i++) {
			if (bids.get(i).getId() == bidID) return bids.get(i);
		}
		return null;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
		//for (int i = 0; i < bids.size(); i++) {
		//	setBid(bids.get(i));
		//}
	}

	public void setBid(Bid bid) {
		bid.setProduct(this);
		this.bids.add(bid);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
			return "Product id " + id + " published: " + published + "Bids: " + this.bids.toString() +"\n";
	}
}
