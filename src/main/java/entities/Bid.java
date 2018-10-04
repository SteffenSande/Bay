package entities;


import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Bid implements Serializable, Comparable<Bid> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;


    @ManyToOne()
    @XmlTransient
    @JoinColumn(name = "auction_fk")
    private Auction auction;

    @ManyToOne
    @XmlTransient
    @JoinColumn(name = "user_fk")
    private User user;

    private double value;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public int getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Bid id " + id + " value: " + value + " time " + time + "\n";
    }

    @Override
    public int compareTo(Bid o) {
        return this.id - o.id;
    }
}
