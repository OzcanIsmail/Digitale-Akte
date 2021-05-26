package de.digitale.akte.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

  @FindBy(id = "userName")
  public WebElement username;

  @FindBy(id = "oldPassword")
  public WebElement password;

  @FindBy(name = "okbtn")
  public WebElement anmelden;

  @FindBy(id = "showcontent")
  public WebElement willkommen;


}
