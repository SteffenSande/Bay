package entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Bid")
public class Bid implements Serializable
{
    private static final long serialVersionUID = 1L;

    //Create elements ids automatically, incremented 1 by 1
    @TableGenerator(
            name = "yourTableGenerator",
            allocationSize = 1,
            initialValue = 1)
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="yourTableGenerator")
    private int id;

    private double value;

    private User seller;

    private User buyer;

    private Date time;


}
