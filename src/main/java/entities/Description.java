package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Embeddable
public class Description implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private double rating;
    @Temporal(TemporalType.DATE)
    private Date endDate;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
}
