package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Embeddable
public class ContactInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private String email;

    @Embedded
    private Address address;

    public ContactInformation() {
    }

    public ContactInformation(String name, String phone, String email, Address address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
