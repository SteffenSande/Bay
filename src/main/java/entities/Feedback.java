package entities;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;


    private String content;
    private double rating;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @ManyToOne
    @JoinColumn(name = "product_fk")
    @XmlInverseReference(mappedBy = "feedbacks")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    @XmlInverseReference(mappedBy = "feedbacks")
    private User user;

    public Feedback() {

    }

    public Feedback(String content, double rating, Date time) {
        this.content = content;
        this.rating = rating;
        this.time = time;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
