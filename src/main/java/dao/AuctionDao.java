package dao;

import entities.Auction;
import entities.Bid;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class AuctionDao extends AbstractDao<Auction, Integer> {

    public AuctionDao() {
        super(Auction.class);
    }

    @Override
    public List<Auction> getAll() {
        return em.createQuery("SELECT item FROM Auction item", tClass).getResultList();
    }
}
