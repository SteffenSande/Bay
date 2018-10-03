package dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T, IdType> {

    public Optional<T> find(IdType id);
    public void merge(T t);
    public void persist(T t);
    public List<T> getAll();

}
