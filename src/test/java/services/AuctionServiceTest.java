package services;

import dao.*;
import entities.Auction;
import entities.Bid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;

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
}