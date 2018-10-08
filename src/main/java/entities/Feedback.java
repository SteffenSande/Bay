package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;


    private String content;
    private double rating;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

   /* @ManyToOne
    @XmlTransient
    @JoinColumn(name = "user_fk")
    private User user;*/

    @OneToOne
    @MapsId
    private Product product;


    public Feedback() {

    }

    public Feedback(String content, double rating, Date time) {
        this.content = content;
        this.rating = rating;
        this.time = time;
    }

    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public void addProduct(Product product) {
        this.product = product;
        product.setFeedback(this);
    }
}
