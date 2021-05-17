package de.digitale.akte.pages;

import de.digitale.akte.utilities.MyDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {

  /**
   * PageFactory design pattern, so the page WebElements are assigned automatically, when it
   * opened. @Param Driver.get()
   */
  public BasePage() {
    PageFactory.initElements(MyDriver.get(), this);
  }

  /** create logger to log infos, errors etc. */
  Logger logger = LoggerFactory.getLogger(BasePage.class);
}
