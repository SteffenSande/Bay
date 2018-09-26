package entities;
import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Embeddable
public class ContactInformation  implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private int id;

	@Embedded
	private Address address;

	private String name;
	private String phone;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
