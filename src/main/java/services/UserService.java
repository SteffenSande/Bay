package services;

import dao.IDao;
import entities.Address;
import entities.ContactInformation;
import entities.ProductCatalog;
import entities.User;

public class UserService implements IUserService {
    private IDao<User, String> userDao;

    @Override
    public User registerUser(String username, String name, String phone, String email, String street, String city, int zip) {
        if (userDao.find(username).isPresent()) {
            throw new IllegalStateException("Username exists already.");
        }
        Address address = new Address(street, city, zip);
        ContactInformation contactInformation = new ContactInformation(name, phone, address);
        ProductCatalog productCatalog = new ProductCatalog();
        User user = new User(username, contactInformation, productCatalog);
        return userDao.save(user);
    }
}
