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

    public String doQueryProducts(){
        queryResult = searchService.searchProductsByTitleAndDescription(query);
        return "/index?faces_redirect=true";
    }

    /**
     * Insert result into two dimensional list
     */
    public List<List<Product>> getNormalisedResults() {
        int columns = 3;
        normalisedResults = new ArrayList<>();
        for (int i = 0; i < queryResult.size(); i++) {
            if (i % columns == 0) {
                normalisedResults.add(new ArrayList<>());
            }
            normalisedResults.get(normalisedResults.size() - 1).add(queryResult.get(i));
        }
        return normalisedResults;
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
