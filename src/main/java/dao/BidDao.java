package dao;

import entities.Bid;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class BidDao extends AbstractDao<Bid, Integer> {

    public BidDao() {
        super(Bid.class);
    }

    @Override
    public List<Bid> getAll() {
        return em.createQuery("SELECT item FROM Bid item", Bid.class).getResultList();
    }
}
