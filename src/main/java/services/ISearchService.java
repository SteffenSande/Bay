package services;

import entities.Product;

import java.util.List;

public interface ISearchService {

    List<Product> searchProductsByTitleAndDescription(String query);

    List<Product> searchProductsByCategory(String query);

}
