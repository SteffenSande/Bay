package dao;

import entities.Product;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class ProductDao extends AbstractDao<Product, Integer> {

    public ProductDao() {
        super(Product.class);
    }

    @Override
    public List<Product> getAll() {
        return em.createQuery("SELECT item FROM Product item", tClass).getResultList();
    }

    public List<Product> getByCategory(String cat) {
        return em.createQuery("SELECT item FROM Product item WHERE item.category = :cat", tClass).setParameter("cat", cat).getResultList();
    }

    public List<String> getUniqueCategories(){
        return em.createQuery("select distinct p.category from Product p", String.class).getResultList();
    }
}
