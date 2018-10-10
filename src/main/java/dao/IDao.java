package dao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface IDao<T, IdType> {

    public Optional<T> find(IdType id);

    /**
     * Looks for the item in the database, throws if not found.
     *
     * @param id
     * @return The item if it is found
     * @throws NoSuchElementException if the element is not found
     */
    public T findOrThrow(IdType id);

    public T save(T t);

    public void persist(T t);

    public List<T> getAll();

    void flush();
}
