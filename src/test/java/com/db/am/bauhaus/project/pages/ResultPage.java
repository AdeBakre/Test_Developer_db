package com.db.am.bauhaus.project.pages;


import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.WebElementLocator;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@DefaultUrl("/")
public class ResultPage extends PageObject{

    @FindBy(xpath="html/body/div[5]/div/div/div/div/div[1]/div/div/div/div/ul[1]/li[2]/a/span")
    WebElementFacade topCategoriesHeader;

    @FindBy(css="h1")
    WebElementFacade allCategoriesHeader;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public String getTopCategoriesHeader() {
        return topCategoriesHeader.getText();

    }

    public String getAllCategoriesHeader() {
        return allCategoriesHeader.getText();

    }

    public void verifyHeaderOnPage(String sub_category){

        assertThat(getAllCategoriesHeader(), containsString(sub_category));
    }
}
