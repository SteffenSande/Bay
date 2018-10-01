package ejb;

import entities.Feature;

import javax.persistence.Query;
import java.util.List;

public class FeatureDao extends AbstractDao<Feature, Integer> {

    @Override
    public List<Feature> getList() {
        Query query = this.em.createQuery("SELECT item FROM Feature item");
        List<Feature> list = query.getResultList();
        return list;
    }

    @Override
    public Feature getItemByPk(Integer id) {
        return this.em.find(Feature.class, id);
    }
}
