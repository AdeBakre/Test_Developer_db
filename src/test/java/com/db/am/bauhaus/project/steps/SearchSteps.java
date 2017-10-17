package com.db.am.bauhaus.project.steps;

import com.db.am.bauhaus.project.SearchFor;
import com.db.am.bauhaus.project.SearchTarget;
import com.db.am.bauhaus.project.SessionVar;
import com.db.am.bauhaus.project.pages.MainSearchPage;
import com.db.am.bauhaus.project.pages.ResultPage;
import com.db.am.bauhaus.project.steplib.SearchUser;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

/**
 * Created by ongshir on 05/10/2016.
 */
public class SearchSteps {

    @Before
    public void before() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Steps
    SearchUser user;
    MainSearchPage mainSearchPage;
    ResultPage resultPage;

    @Given("^John is viewing the Etsy landing page$")
    public void goto_landing_page() {
        mainSearchPage.open();
    }

    @Given("^([^\\s]+) is viewing the Etsy landing page \\(screenplay\\)$")
    public void goto_landing_page_screenplay(String theUser) {
        theActorCalled(theUser).attemptsTo(Open.browserOn().the(mainSearchPage));


    }

    @When("^he searches for a product from the input box$")
    public void search_from_input_box() {
        user.search_from_input_box();
    }

    @When("^he searches for a product from the input box \\(screenplay\\)$")
    public void search_from_input_box_screenplay() {
        theActorInTheSpotlight().attemptsTo(SearchFor.randomText());
    }

    @Then("^the result should be displayed$")
    public void verify_search_result() {

        //user.verify_result_for_top_categories();
        user.verify_result_for_all_categories();
        //mainSearchPage.navigate_to_category_sub_page();

    }

    @Then("^the result should be displayed \\(screenplay\\)$")
    public void verify_search_result_screenplay() {
        String searchText = Serenity.sessionVariableCalled(SessionVar.SEARCH_TEXT).toString();
        theActorInTheSpotlight().should(
                seeThat("the top categories header ", the(SearchTarget.TOP_CATEGORIES_HEADER), containsText(searchText)),
                seeThat("the all categories header ", the(SearchTarget.ALL_CATEGORIES_HEADER), containsText(searchText))
        );
    }

    @When("^he clicks on the \"([^\"]*)\" with \"([^\"]*)\"$")
    public void he_clicks_on_the_on_the_page(String menu, String subMenu) throws Throwable {

        mainSearchPage.navigate_to_category_sub_page(menu, subMenu);

    }

    @When("^he clicks on \"([^\"]*)\"$")
    public void he_clicks_on(String icon_name) throws Throwable {
        mainSearchPage.select_by_icon(icon_name);
    }


    @Then("^he should be on the \"([^\"]*)\" page$")
    public void he_should_be_on_the_page(String pageHeader) throws Throwable {

        resultPage.verifyHeaderOnPage(pageHeader);


    }

    @Then("^he should be on the page for menu$")
    public void he_should_be_on_the_page_for_menu() throws Throwable {

        user.verify_result_for_top_categories();

    }

    /*
    API Section
     */

    @Given("^John requested a search for all top categories$")
    public void john_requested_a_search_for_all_top_categories() throws Throwable {
        user.get_all_top_categories();
    }

    @Then("^the service should return results for top categories$")
    public void the_service_should_return_results_for_top_categories() throws Throwable {
        user.verifyCategoriesListNotEmpty();
    }

    @Given("^John searched for categories by \"([^\"]*)\" with \"([^\"]*)\"$")
    public void john_searched_for_categories_by_with(String item, String keyword) throws Throwable {
        user.get_category_by_name(item,keyword);
    }

    @Then("^the result should contain categories with \"([^\"]*)\"$")
    public void the_result_should_contain_categories_with(String keyword) throws Throwable {
        user.verify_match_exists(keyword);
    }



}
