package dao;

import setup.Configuration;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public abstract class AbstractDao <T, IdType> implements IDao<T, IdType> {

    @PersistenceContext(unitName= Configuration.CURRENT_PERSISTENCE_UNIT)
    protected EntityManager em;

    protected Class<T> tClass;

    public AbstractDao(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public Optional<T> find(IdType id) {
        return Optional.ofNullable(em.find(tClass, id));
    }

    @Override
    public void persist(T item) {
        em.persist(item);
    }

    @Override
    public void merge(T t) {
        em.merge(t);
    }
}