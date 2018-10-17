package entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name = "auctions")
@XmlSeeAlso(Auction.class)
public class Auctions{

    private static final long serialVersionUID = 1L;

    Collection < Auction > auctions;

    public Auctions() {
        super();
        auctions = new ArrayList<>();
    }

    public Auctions(Collection <Auction> c) {
        super();
        this.auctions = c;
    }

    @XmlElement(name = "auction")
    public Collection<Auction> getAuctions() {
        return this.auctions;
    }
    public void setAucitons(List<Auction> auc) {
        this.auctions.addAll(auc);
    }

    @Override
    public String toString() {
        return "Auctions{" +
                "auctions=" + auctions +
                '}';
    }
}
