package controllers;

import entities.Product;
import services.ISearchService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class SearchController {

    @EJB
    ISearchService searchService;

    private String query = "";
    private List<Product> queryResult = new ArrayList<>();
    private List<List<Product>> normalisedResults = new ArrayList<>();

    public void doQueryProducts(){
        queryResult = searchService.searchProductsByTitleAndDescription(query);
    }

    /**
     * Insert result into two dimensional list
     */
    public List<List<Product>> getNormalisedResults() {
        int columns = 3;
        List<List<Product>> results = new ArrayList<>();
        for (int i = 0; i < queryResult.size(); i++) {
            if (i % columns == 0) {
                results.add(new ArrayList<>());
            }
            results.get(results.size() - 1).add(queryResult.get(i));
        }
        return results;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Product> getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(List<Product> queryResult) {
        this.queryResult = queryResult;
    }
}
