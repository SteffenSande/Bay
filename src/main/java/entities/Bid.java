package entities;


import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Bid implements Serializable, Comparable<Bid> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;


    @ManyToOne
    @JoinColumn(name = "auction_fk")
    @XmlInverseReference(mappedBy = "bids")
    @XmlElement
    private Auction auction;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    @XmlInverseReference(mappedBy = "bids")
    private User user;

    private double value;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    public Bid() {
    }

    public Bid(Auction auction, User user, double value, Date time) {
        this.auction = auction;
        this.user = user;
        this.value = value;
        this.time = time;
    }

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
        return "Bid id " + id;
    }

    /**  */
    @Override
    public int compareTo(Bid o) {
        return (value != o.value) ? Double.compare(value, o.value) : o.time.compareTo(time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return Double.compare(bid.value, value) == 0 &&
                Objects.equals(auction, bid.auction) &&
                Objects.equals(user, bid.user) &&
                Objects.equals(time, bid.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auction, user, value, time);
    }
}
