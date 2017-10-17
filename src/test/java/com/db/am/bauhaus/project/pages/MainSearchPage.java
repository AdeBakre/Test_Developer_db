package com.db.am.bauhaus.project.pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ongshir on 05/10/2016.
 */
@DefaultUrl("/")
public class MainSearchPage extends PageObject {

    @FindBy(id = "search-query")
    WebElementFacade inputBox;

//    @FindBy(css = ".btn.btn-orange.btn-append") //Sample code which no longer works
    @FindBy(css=".btn.btn-primary")
    WebElementFacade searchButton;

    @FindBy(id="cnav-header-inner")
    WebElementFacade menuBar;



    public MainSearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchFromInputBox(String searchText) {
        inputBox.waitUntilPresent().sendKeys(searchText);
        searchButton.click();
    }

    public void navigate_to_category_sub_page(String categoryText, String sub_category_text){

        List<WebElementFacade> myElements = findAll(".catnav-primary-item");

        for (WebElementFacade webElementFacade : myElements) {

            try{
                String menuNames =  webElementFacade.getText();
                if(menuNames.contains(categoryText)){


                    System.out.println("My point is:"+ menuNames);


                    webElementFacade.click();

                    select_sub_category(sub_category_text);

                    break;
                }

            }
           catch(ElementShouldBeVisibleException ee){
                ee.getStackTrace();

           }


        }

    }

    private void select_sub_category(String selection){

        List<WebElementFacade> myInnerElements = findAll(".catnav-dropdown-inner .catnav-sidebar-list>li>a");

        for(WebElementFacade subCat: myInnerElements){

            try{
                String subMenuText = subCat.getText();

                if(subMenuText.contains(selection)){

                    subCat.click();
                    break;
                }

            }
            catch(ElementShouldBeVisibleException ere){
                ere.getStackTrace();
            }
        }

    }

    public void select_by_icon(String icon_name){

        scrollToElementOnPage(700);

        List<WebElementFacade> allIcons = findAll(".card-meta-row .vesta-hp-category-title");
        for(WebElementFacade icon : allIcons){

            try{
                String iconText = icon.getText();

                if(iconText.contains(icon_name)){

                    icon.waitUntilVisible().click();
                    break;
                }
            }
            catch (ElementShouldBeVisibleException ica){
                ica.getStackTrace();
            }
        }
    }

    private void scrollToElementOnPage(int pixelpoint){

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,"+pixelpoint+"); return true");
    }

}
