package controllers;

import dao.AuctionDao;
import dao.ProductDao;

import dao.UserDao;
import entities.Auction;
import entities.Bid;
import entities.Product;
import entities.User;
import services.AuctionService;
import services.IAuctionService;
import util.Pair;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SessionScoped
@ManagedBean
public class BidController implements Serializable {

    private User user;
    private Auction auction;

    @Inject
    AuctionDao auctionDao;

    @Inject
    UserDao userDao;
    private int id;


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private int amount;


    @Inject
    private IAuctionService auctionService;

    @Inject
    private ProductDao productDao;

    public BidController() {

    }

    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        this.user = userDao.findUserById(request.getUserPrincipal().getName());
        this.auction = auctionDao.findOrThrow(Integer.parseInt(request.getParameter("id")));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public double findHighestBid(int auction){
        try{
            return auctionService.getHighestBid(productDao.findOrThrow(auction).getAuction()).get().getValue();
        }catch (Exception e){
            System.out.println("No highest bid");
        }
        return 0.0;
    }

    public String placeBid() {
        auctionService.placeBid(id, user, amount);
        return "/auction.xhtml?faces-redirect=true&id="+id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}