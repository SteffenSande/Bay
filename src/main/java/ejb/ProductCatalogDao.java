package ejb;

import entities.ProductCatalog;

import javax.persistence.Query;
import java.util.List;

public class ProductCatalogDao extends AbstractDao<ProductCatalog, Integer> {

    @Override
    public List<ProductCatalog> getList() {
        Query query = this.em.createQuery("SELECT item FROM ProductCatalog item");
        List<ProductCatalog> list = query.getResultList();
        return list;
    }

    @Override
    public ProductCatalog getItemByPk(Integer id) {
        return this.em.find(ProductCatalog.class, id);
    }
}
