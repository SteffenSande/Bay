package entities;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BidTest {


    @Test
    void compareToDifferentValues() {
        Bid lowBid = new Bid(null, null, 10, new Date());
        Bid highBid = new Bid(null, null, 20, new Date());

        bidIsFirstAfterSorting(lowBid, highBid, lowBid);
    }

    @Test
    void compareToSameValues() {
        Bid earlyBid = new Bid(null, null, 10, new Date(1000));
        Bid lateBid = new Bid(null, null, 10, new Date(2000));

        bidIsFirstAfterSorting(earlyBid, lateBid, lateBid);
    }



    private void bidIsFirstAfterSorting(Bid a, Bid b, Bid expectedFirst) {
        List<Bid> l1 = Arrays.asList(a, b);
        List<Bid> l2 = Arrays.asList(b, a);
        Collections.sort(l1);
        Collections.sort(l2);
        assertEquals(expectedFirst, l1.get(0));
        assertEquals(expectedFirst, l2.get(0));
    }

}