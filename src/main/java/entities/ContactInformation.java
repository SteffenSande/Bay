package entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class ContactInformation  implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
