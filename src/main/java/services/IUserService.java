package services;

import entities.Product;
import entities.User;

import java.util.List;

public interface IUserService {

    User registerUser(String username, String name, String phone, String email, String street, String city, int zip);

    List<Product> productsBidOn(User user);

}
