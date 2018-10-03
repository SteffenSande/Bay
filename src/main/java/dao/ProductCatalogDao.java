package dao;

import entities.ProductCatalog;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.util.List;

@RequestScoped
public class ProductCatalogDao extends AbstractDao<ProductCatalog, Integer> {

    public ProductCatalogDao() {
        super(ProductCatalog.class);
    }

    @Override
    public List<ProductCatalog> getAll() {
        return em.createQuery("SELECT item FROM ProductCatalog item", tClass).getResultList();
    }
}
