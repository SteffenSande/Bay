package dao;

import entities.AppUser;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class AppUserDao extends AbstractDao<AppUser, Integer> {

    public AppUserDao() {
        super(AppUser.class);
    }

    @Override
    public List<AppUser> getAll() {
        return this.em.createQuery("SELECT item FROM AppUser item", tClass).getResultList();
    }
}
