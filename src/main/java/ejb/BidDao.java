package ejb;

import entities.Bid;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class BidDao extends AbstractDao<Bid, Integer> {

    @Override
    public List<Bid> getList() {
        Query query = this.em.createQuery("SELECT item FROM Bid item");
        List<Bid> bid = query.getResultList();
        return bid;
    }

    @Override
    public Bid getItemByPk(Integer id) {
        return this.em.find(Bid.class, id);
    }

    public List<Bid> getAllBidsForProduct(int aid) {
        List<Bid> bids = getList();
        List<Bid> result = new ArrayList<>();
        for (int i = 0; i < bids.size(); i++) {
            if (bids.get(i).getProduct().getId() == aid) {
                result.add(bids.get(i));
            }
        }
        return result;
    }
}
