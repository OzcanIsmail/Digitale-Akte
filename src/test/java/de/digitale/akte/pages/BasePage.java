package de.digitale.akte.pages;

import de.digitale.akte.utilities.MyDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

@FindBy(xpath = "//div[text()='Ich stimme zu']")
    public List<WebElement> ichStimmeZu;
}
