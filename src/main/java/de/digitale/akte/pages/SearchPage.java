package de.digitale.akte.pages;

import de.digitale.akte.utilities.MyDriver;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

  @FindBy(xpath = "//*[@name='q']")
  public WebElement searchBox;

  @FindBy(id = "result-stats")
  public WebElement totalResults;

  @FindBy(xpath = "//div[text()='Ich stimme zu']")
  public List<WebElement> ichStimmeZu;

  public List<WebElement> searchResults(String word) {
    return MyDriver.get().findElements(By.xpath("//h3[contains(text(),'" + word + "')]"));
  }
}
