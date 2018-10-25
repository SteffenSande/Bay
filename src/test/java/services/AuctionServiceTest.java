package services;

import dao.*;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuctionServiceTest {

    AuctionService auctionService;

    @BeforeEach
    void setup() {
        auctionService = new AuctionService();
        auctionService.auctionDao = mock(AuctionDao.class);
        auctionService.bidDao = mock(BidDao.class);
        auctionService.feedbackDao = mock(FeedbackDao.class);
        auctionService.productDao = mock(ProductDao.class);
        auctionService.userDao = mock(UserDao.class);
    }

    @Test
    void placeBidStoresBid() {
        Auction auction = new Auction();
        auction.setBids(new ArrayList<>());
        when(auctionService.auctionDao.findOrThrow(Matchers.anyInt())).thenReturn(auction);
        auctionService.placeBid(1, 10);

        ArgumentCaptor<Bid> bid = ArgumentCaptor.forClass(Bid.class);
        verify(auctionService.bidDao).save(bid.capture());
        assertEquals(auction, bid.getValue().getAuction());
        assertEquals(10, bid.getValue().getValue());
    }

    @Test
    void placeFeedbackWithoutWinningThrows() {
        Auction auction = new Auction();
        auction.setBids(new ArrayList<>());
        Product product = new Product();
        product.setAuction(auction);

        when(auctionService.productDao.findOrThrow(Matchers.anyInt())).thenReturn(product);

        assertThrows(IllegalArgumentException.class, () -> auctionService.placeFeedback(0, "", 0.0, ""));
    }

    @Test
    void placeFeedbackStoresFeedback() {
        Auction auction = new Auction();
        Product product = new Product();
        product.setDescription(new Description());
        product.setAuction(auction);
        User user = new User();
        auction.setBids(Arrays.asList(new Bid(auction, user, 200, new Date())));

        when(auctionService.userDao.findOrThrow(Matchers.anyString())).thenReturn(user);
        when(auctionService.productDao.findOrThrow(Matchers.anyInt())).thenReturn(product);

        auctionService.placeFeedback(0, "some content", 5.0, "");

        ArgumentCaptor<Feedback> arg = ArgumentCaptor.forClass(Feedback.class);
        verify(auctionService.feedbackDao).persist(arg.capture());
        Feedback feedback = arg.getValue();

        assertEquals("some content", feedback.getContent());
    }
}