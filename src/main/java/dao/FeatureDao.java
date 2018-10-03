package dao;

import entities.Feature;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.util.List;

@RequestScoped
public class FeatureDao extends AbstractDao<Feature, Integer> {

    public FeatureDao() {
        super(Feature.class);
    }

    @Override
    public List<Feature> getAll() {
        return em.createQuery("SELECT item FROM Feature item", tClass).getResultList();
    }
}
