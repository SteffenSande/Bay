package entities;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

@Embeddable
public class Description  implements Serializable
{
    private static final long serialVersionUID = 1L;

	private String title;
	private double rating;
    @Temporal(TemporalType.DATE)
	private Date endDate;
	
	//@OneToMany
	//@JoinColumn(name="feature_id")
	//private List <Feature> features = new ArrayList<>();
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
//	public List <Feature> getFeatures() {
//		return features;
//	}
//	public void setFeatures(List <Feature> features) {
//		this.features = features;
//	}

}
