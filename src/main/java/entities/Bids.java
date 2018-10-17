package entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name = "bids")
@XmlSeeAlso(Bid.class)
public class Bids{

    private static final long serialVersionUID = 1L;

    Collection <Bid> bids;

    public Bids() {
        super();
        bids = new ArrayList<>();
    }

    public Bids (Collection <Bid> c) {
        super();
        this.bids = c;
    }

    @XmlElement(name = "bid")
    public Collection<Bid> getBids() {
        return this.bids;
    }
    public void setAucitons(List<Bid> bids) {
        this.bids.addAll(bids);
    }

}
