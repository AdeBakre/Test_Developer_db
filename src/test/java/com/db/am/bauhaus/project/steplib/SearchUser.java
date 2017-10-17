package com.db.am.bauhaus.project.steplib;

import com.db.am.bauhaus.project.pages.MainSearchPage;
import com.db.am.bauhaus.project.pages.ResultPage;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.ResponseBodyExtractionOptions;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

/**
 * Created by ongshir on 05/10/2016.
 */
public class SearchUser extends RestServices {

    MainSearchPage mainSearchPage;
    ResultPage resultPage;
    RestServices restServices;


    String searchText = "craft";



    @Step
    public void search_from_input_box() {
        mainSearchPage.searchFromInputBox(searchText);
    }

    @Step
    public void verify_result_for_top_categories() {

        assertThat(resultPage.getTopCategoriesHeader(), containsString(searchText));
        //assertThat(mainSearchPage.getTopCategoriesHeader(), containsString(searchText));

    }

    @Step
    public void verify_result_for_all_categories() {
        //assertThat(mainSearchPage.getAllCategoriesHeader(), containsString(searchText));
        assertThat(resultPage.getAllCategoriesHeader(), containsString(searchText));
    }


    /*
    API Section
     */

    public ResponseBodyExtractionOptions response;

    @Step
    public void verifyCategoriesListNotEmpty(){
        System.out.println("categories: " + response.asString());
        assertThat(isResponseEmpty(response),is(false));
    }


    @Step
    public void get_all_top_categories() {


        response = getResponse("taxonomy/categories/?api_key=1ukaxmbyfnupokp5yernib65");
    }

    @Step
    public void get_category_by_name(String endpoint, String keyword){


        response = getResponse(endpoint+"/"+keyword);

    }

    @Step
    public void get_subCategory_name(String endpoint, String category_keyword, String sub_category_keyword){

        response = restServices.getResponse(endpoint+"/"+category_keyword+"/"+sub_category_keyword);
    }

    @Step
    public void verify_match_exists(String query){

        assertThat(getResponseAsString(), containsString(query));
    }

    @Step
    public void verify_match_exists_with_key(String key, String value) {
        assertThat(isMatchFound(key, value), is(true));
    }

    private boolean isMatchFound(String key, String value) {
        return getCategoryList(key).stream().anyMatch(
                category -> category.equalsIgnoreCase(value)
        );
    }

    private ResponseBodyExtractionOptions getCategoryLists(String endpoint){

        return restServices.getResponse(endpoint);
    }

//    private ResponseBodyExtractionOptions getSubCategoryLists(String endpoint_tag, String endpoint_subtag){
//
//        return restServices.getResponse(endpoint_tag, endpoint_subtag);
//    }

    private List<String> getCategoryList(String key) {
        return JsonPath.from(getResponseAsString()).getList(key);
    }

    private String getResponseAsString(){

        return response.asString();
    }



}
