package ejb;

import entities.AppUser;

import javax.persistence.Query;
import java.util.List;

public class AppUserDao extends AbstractDao<AppUser, Integer> {

    @Override
    public List<AppUser> getList() {
        Query query = this.em.createQuery("SELECT item FROM AppUser item");
        List<AppUser> list = query.getResultList();
        return list;
    }

    @Override
    public AppUser getItemByPk (Integer id){
        return this.em.find(AppUser.class,id.intValue());
    }
}
