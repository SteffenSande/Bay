package services;

import dao.*;
import entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    void placeBidThrowsOnIllegalAuctionId() {
        when(auctionService.auctionDao.find(Matchers.anyInt()))
                .thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> auctionService.placeBid(0, 0));
    }

    @Test
    void placeBidStoresBid() {
        Auction mockedAuction = mock(Auction.class);
        when(auctionService.auctionDao.find(Matchers.anyInt()))
                .thenReturn(Optional.of(mockedAuction));
        auctionService.placeBid(1, 10);

        ArgumentCaptor<Bid> bid = ArgumentCaptor.forClass(Bid.class);
        verify(auctionService.bidDao).save(bid.capture());
        assertEquals(mockedAuction, bid.getValue().getAuction());
        assertEquals(10, bid.getValue().getValue());
    }

    @Test
    void placeFeedbackThrowsOnIllegalAuctionId() {
        Auction mockedAuction = mock(Auction.class);
        Product mockedProduct = mock(Product.class);
        User mockedUser = mock(User.class);

        when(auctionService.auctionDao.find(Matchers.anyInt()))
                .thenReturn(Optional.empty());
        when(auctionService.userDao.find(Matchers.anyInt()))
                .thenReturn(Optional.empty());
        when(auctionService.productDao.find(Matchers.anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> auctionService.placeFeedback(0, "", 5.0, 0 ));
    }

    @Test
    void placeFeedbackStoresFeedback() {
        Auction mockedAuction = mock(Auction.class);
        Product mockedProduct = mock(Product.class);
        User mockedUser = mock(User.class);


        when(auctionService.userDao.find(Matchers.anyInt()))
                .thenReturn(Optional.of(mockedUser));
        when(auctionService.productDao.find(Matchers.anyInt()))
                .thenReturn(Optional.of(mockedProduct));
        when(mockedProduct.getAuction())
                .thenReturn(mockedAuction);

        auctionService.placeFeedback(0, "", 5.0, 0);

        verify(mockedAuction).getBids();
        verify(mockedProduct).getAuction();
        verify(mockedUser).getBids();

    }
}