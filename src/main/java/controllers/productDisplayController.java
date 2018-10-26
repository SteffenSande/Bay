package controllers;

import dao.AuctionDao;
import dao.ProductDao;

import entities.Auction;
import entities.Bid;
import entities.Product;
import services.AuctionService;
import services.IAuctionService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@ManagedBean
@SessionScoped
public class productDisplayController implements Serializable {

    @EJB
    IAuctionService auctionService;

    private List<Product> products;
    private String category;
    private Auction auction;
    private Product product;
    private int id;


    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Inject
    private ProductDao productDao;

    public void findAuction() {
        this.product = productDao.find(id).get();
        auction = product.getAuction();
    }

    public boolean getAuctionDone() {
        Date now = new Date();
        return product.getDescription().getEndDate().before(now);
    }

    public String showCategory(String category){
        this.category = category;
        this.products = productDao.getByCategory(category);
        return "index?faces_redirect=true";
    }

    public productDisplayController() {


    }

    public Auction showAuction(){
        return auction;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWinner() {
        if (!getAuctionDone()) {
            return "The auction is still ongoing";
        }
        Optional<Bid> winnerBid = auctionService.getHighestBid(auction);
        return winnerBid
                .map(b -> b.getUser().getContactInformation().getName() + " with his/her bid on " + b.getValue())
                .orElse("There was no bids!");
    }

    public String getEndTime() {
        if (!getAuctionDone()) {
            return "The auction is still ongoing";
        }
        return new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(product.getDescription().getEndDate());
    }
}