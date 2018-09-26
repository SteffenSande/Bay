package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Address  implements Serializable
{
    private static final long serialVersionUID = 1L;
   
    private String street;
	private String city;
    private int zip;

}
