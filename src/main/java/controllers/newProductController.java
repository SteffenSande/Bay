package controllers;

import dao.ProductDao;
import entities.Description;
import entities.Product;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SessionScoped
@ManagedBean
public class newProductController {
    private String title;
    private String content;
    private String picturePath;
    private String extras;
    private String category;
    private Date endDate;
    private List<String> categories;

    @Inject
    ProductDao productDao;

    public newProductController(){
        this.categories = new ArrayList<>();
    }

    @PostConstruct
    void init(){
        this.categories =productDao.getUniqueCategories();
        this.picturePath = "https://cdn.vox-cdn.com/thumbor/qZdDl3n1ctNCLpwVYlqOuz4Utyg=/0x0:1024x683/1820x1213/filters:focal(431x261:593x423)/cdn.vox-cdn.com/uploads/chorus_image/image/59318659/iphone8red_1024.1523277341.jpg";
    }

    public void storeProduct(){
        Description description = new Description();
        description.setRating(0);
        description.setTitle(title);
        description.setContent(content);
        Product product = new Product(false, picturePath, extras,category, description);
        productDao.persist(product);
    }



    public List<String> getCategories(){
        return this.categories;
    }

    public String getTitle() {
        return title;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
