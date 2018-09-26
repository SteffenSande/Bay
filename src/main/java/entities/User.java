package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

	@Embedded
	private ContactInformation contactInformation;

	private String username;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
