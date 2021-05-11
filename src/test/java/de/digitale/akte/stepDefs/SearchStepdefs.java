package de.digitale.akte.stepDefs;

import de.digitale.akte.pages.SearchPage;
import de.digitale.akte.utilities.BrowserUtilities;
import de.digitale.akte.utilities.MyDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SearchStepdefs {

    Logger logger = LoggerFactory.getLogger(SearchStepdefs.class);

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        MyDriver.get().get(url);
        logger.info("User is on google home page");
        SearchPage searchPage = new SearchPage();
        if (searchPage.ichStimmeZu.size()>0){
            searchPage.ichStimmeZu.get(0).click();
            logger.info("User clicked on 'ich stimme zu' button");
        }
    }

    @And("I type {string}")
    public void iType(String searchQuery) {
        new SearchPage().searchBox.sendKeys(searchQuery);
        logger.info("User typed '{}' in search box",searchQuery);
    }

    @And("I press Enter")
    public void iPressEnter() {
        new SearchPage().searchBox.sendKeys(Keys.ENTER);
        logger.info("User clicked on enter");
    }

    @Then("I should be shown results including {string}")
    public void iShouldBeShownResultsIncluding(String search) {
        SearchPage searchPage = new SearchPage();
        Assert.assertTrue("Results message verification ", (searchPage.totalResults.isDisplayed()));
        logger.info("Results message is verified");
        List<WebElement> webElements = searchPage.searchResults(search);
        List<String> elementsText = BrowserUtilities.getElementsText(webElements);
        elementsText.forEach(each -> {
            Assert.assertTrue("Search result verification", each.contains(search));
        });
        logger.info("Every search result on first page verified");



    }
}
