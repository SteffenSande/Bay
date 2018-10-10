package services;

import entities.User;

public interface IUserService {

    User registerUser(String username, String name, String phone, String email, String street, String city, int zip);

}
