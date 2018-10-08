package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class noe implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue
    int id;

    private String hei;


    public String getHei() {
        return hei;
    }

    public void setHei(String hei) {
        this.hei = hei;
    }
}
