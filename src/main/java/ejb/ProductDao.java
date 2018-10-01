package ejb;

import entities.Product;

import javax.persistence.Query;
import java.util.List;

public class ProductDao extends AbstractDao<Product, Integer> {

    @Override
    public List<Product> getList() {
        Query query = this.em.createQuery("SELECT item FROM Product item");
        List<Product> list = query.getResultList();
        return list;
    }

    @Override
    public Product getItemByPk(Integer id){
        return this.em.find(Product.class,id);
    }
}
