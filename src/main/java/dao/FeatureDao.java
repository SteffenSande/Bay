package dao;

import entities.PracticalInfo;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class FeatureDao extends AbstractDao<PracticalInfo, Integer> {

    public FeatureDao() {
        super(PracticalInfo.class);
    }

    @Override
    public List<PracticalInfo> getAll() {
        return em.createQuery("SELECT item FROM PracticalInfo item", tClass).getResultList();
    }
}
