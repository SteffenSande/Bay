package ejb;

import entities.Bid;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Date;

@Startup
@Singleton
public class startup {

    @EJB
    private BidDao bd;
    private Bid bid = new Bid();

    @PostConstruct
    void init(){
        bid.setValue(1234);
        bid.setTime(new Date());
        bd.persist(bid);
    }
}
