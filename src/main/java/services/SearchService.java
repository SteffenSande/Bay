package services;

import dao.IDao;
import entities.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class SearchService implements ISearchService {

    @Inject
    IDao<Product, Integer> productDao;

    @Override
    public List<Product> searchProductsByTitleAndDescription(String query) {
        return productDao.getAll()
                .stream()
                .filter(p -> productMatches(p, query))
                .collect(Collectors.toList());
    }

    private boolean productMatches(Product p, String query) {
        query = query.toLowerCase();
        return p.getDescription().getTitle().toLowerCase().contains(query)
                || p.getDescription().getContent().toLowerCase().contains(query);
    }

    @Override
    public List<Product> searchProductsByCategory(String query) {
        return null;
    }
}
