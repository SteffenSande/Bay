package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;


    private String content;
    private double rating;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Feedback() {

    }

    public Feedback(String content, double rating, Date time) {
        this.content = content;
        this.rating = rating;
        this.time = time;
        this.id = 1;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
