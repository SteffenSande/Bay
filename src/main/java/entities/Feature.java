package entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
public class Feature implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;


    //Kanskje bytte til enum og klassenavn til categori
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_fk")
    private Product product;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getFeature() {
        return value;
    }

    public void setFeature(String feature) {
        this.value = feature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
