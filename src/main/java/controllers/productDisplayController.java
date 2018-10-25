package controllers;

import dao.ProductDao;

import entities.Product;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Named
@RequestScoped
public class productDisplayController {

    private List<Product> products;
    private String category;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}