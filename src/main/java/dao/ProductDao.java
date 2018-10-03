package dao;

import entities.Product;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
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
}
