package soap;

import com.sun.tools.javac.util.Pair;
import dao.AuctionDao;
import dao.BidDao;
import dao.IDao;
import entities.Bid;
import services.IAuctionService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@WebService
public class Auction {

    @Inject
    IDao<entities.Auction, Integer> auctionDao;

    @Inject
    IDao<Bid, Integer> bidDao;

    @EJB
    IAuctionService auctionService;

    @WebMethod
    public List<entities.Auction> getAuctions() {
        return auctionDao.getAll();
    }

    @WebMethod
    public entities.Auction getAuction(int id) {
        return auctionDao.find(id).orElseThrow(NoSuchElementException::new);
    }

    @WebMethod
    @Transactional
    public String placeBid(int auctionId, int value) {
        Pair<Bid, Boolean> p = auctionService.placeBid(auctionId, value);
        return p.snd ? "This is currently the highest bid." : "Someone has bid higher";
    }
}
