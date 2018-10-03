package dao;

import entities.User;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class UserDao extends AbstractDao<User, Integer> {

    public UserDao() {
        super(User.class);
    }

    @Override
    public List<User> getAll() {
        return this.em.createQuery("SELECT item FROM User item", tClass).getResultList();
    }
}
