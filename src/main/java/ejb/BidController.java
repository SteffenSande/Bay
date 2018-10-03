package ejb;

import dao.AuctionDao;
import entities.Bid;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "bidController")
@RequestScoped
public class BidController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AuctionDao bidDao;

    private Bid bid;

    public List<Bid> getBids() {
        List<Bid> reverseTweetList = new ArrayList<>();
        reverseTweetList.addAll(this.bidDao.getAllBids());
        Collections.reverse(reverseTweetList);
        return reverseTweetList;
    }

    public String saveBids() {
        this.bidDao.persist(this.bid);
        return "index";
    }

    public Bid getBid() {
        if (this.bid == null) {
            bid = new Bid();
        }
        return bid;

    }

}
