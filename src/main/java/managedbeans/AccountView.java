package managedbeans;

import entities.Product;
import services.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class AccountView implements Serializable {

    private static final long serialVersionUID = 3254181245309041387L;

    public int getNumber() {
        return 0;
    }

}
