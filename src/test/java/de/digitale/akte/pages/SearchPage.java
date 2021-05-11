package de.digitale.akte.pages;

import de.digitale.akte.utilities.MyDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(name = "q")
    public WebElement searchBox;

    @FindBy(id = "result-stats")
    public WebElement totalResults;

    public List<WebElement> searchResults(String word){
        return MyDriver.get().findElements(By.xpath("//h3[contains(text(),'"+word+"')]"));
    }
}
