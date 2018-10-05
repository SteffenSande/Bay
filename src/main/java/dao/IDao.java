package dao;

import java.util.List;
import java.util.Optional;

public interface IDao<T, IdType> {

    public Optional<T> find(IdType id);

    public T save(T t);

    public void persist(T t);

    public List<T> getAll();

    void flush();
}
