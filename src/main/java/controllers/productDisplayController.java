package controllers;

import dao.AuctionDao;
import dao.ProductDao;

import entities.Auction;
import entities.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Named
@RequestScoped
public class productDisplayController implements Serializable {

    private List<Product> products;
    private String category;
    private Auction auction;
    private Product product;

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

    public String showCategory(String category){
        this.category = category;
        this.products = productDao.getByCategory(category);
        return "index?faces_redirect=true";
    }

    public Auction showAuction(int id){
        this.product = productDao.find(id).get();
        auction = product.getAuction();
        return auction;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}