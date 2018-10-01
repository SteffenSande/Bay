package ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractDao <T, IdT> {

    static final String DAT250_PSQL_UNIT_NAME = "dat250psql";

    @PersistenceContext(unitName=DAT250_PSQL_UNIT_NAME)
    protected EntityManager em;

    public void persist(T item) {
        em.persist(item);
    }

    public abstract List<T> getList();
    public abstract T getItemByPk(IdT id);

}