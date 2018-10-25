package dao;

import setup.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class AbstractDao<T, IdType> implements IDao<T, IdType> {

    @PersistenceContext(unitName = Configuration.CURRENT_PERSISTENCE_UNIT)
    protected EntityManager em;

    protected Class<T> tClass;

    public AbstractDao() {}

    public AbstractDao(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public Optional<T> find(IdType id) {
        return Optional.ofNullable(em.find(tClass, id));
    }

    @Override
    public T findOrThrow(IdType id) {
        return find(id).orElseThrow(() -> new NoSuchElementException(tClass.getName() + " with id " + id.toString() + " does not exist."));
    }

    @Override
    public void persist(T item) {
        em.persist(item);
    }

    @Override
    public T save(T t) {
        return em.merge(t);
    }

    @Override
    public void flush() {
        em.flush();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class<T> gettClass() {
        return tClass;
    }

    public void settClass(Class<T> tClass) {
        this.tClass = tClass;
    }
}