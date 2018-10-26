package services;

import dao.IDao;
import entities.*;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
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

    @Override
    public List<Product> productsBidOn(User user) {
        return user.getBids().stream()
                .map(Bid::getAuction)
                .map(Auction::getProduct)
                .distinct()
                .collect(Collectors.toList());
    }
}
