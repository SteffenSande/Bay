package soap;

import dao.IDao;
import dao.UserDao;
import entities.Auctions;
import entities.User;
import services.IAuctionService;
import util.Pair;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.NoSuchElementException;


@WebService(

)
public class Auction {

    @Inject
    IDao<entities.Auction, Integer> auctionDao;

    @Inject
    IDao<entities.Bid, Integer> bidDao;

    @Inject
    IDao<User, String> userDao;

    @EJB
    IAuctionService auctionService;

    @WebMethod
    public Auctions getAuctions() {
        Auctions auctions = new Auctions(auctionDao.getAll());
        return auctions;

    }
    @WebMethod
    public entities.Auction getAuction(int id) {
        return auctionDao.find(id).orElseThrow(NoSuchElementException::new);
    }

    @WebMethod
    public User getUser(String username) {
        return userDao.find(username).orElseThrow(NoSuchElementException::new);
    }

    @WebMethod
    @Transactional
    public String placeBid(int auctionId, User user, int value) {
        Pair<entities.Bid, Boolean> p = auctionService.placeBid(auctionId, user, value);
        return p.snd() ? "This is currently the highest bid." : "Someone has bid higher";
    }
}



