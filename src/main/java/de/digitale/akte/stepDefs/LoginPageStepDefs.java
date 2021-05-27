package de.digitale.akte.stepDefs;

import de.digitale.akte.pages.LoginPage;
import de.digitale.akte.utilities.BrowserUtilities;
import de.digitale.akte.utilities.MyDriver;
import io.cucumber.java.de.Dann;
import io.cucumber.java.de.Gegebensei;
import io.cucumber.java.de.Und;
import io.cucumber.java.de.Wenn;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPageStepDefs {

    Logger logger = LoggerFactory.getLogger(LoginPageStepDefs.class);

    @Gegebensei("User ist auf Anmeldungseite")
    public void user_ist_auf_anmeldungseite() {
        String url = "https://nscale-dab-mandant1-test.apps.ocp.devda.aced.de.atos.net/nscale_web/nw/login";
        MyDriver.get().get(url);
        logger.info("User is on Loginpage");
    }

    @Wenn("User seine {string} und {string} eingibt")
    public void userSeineUndEingibt(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.username.sendKeys(username);
        logger.info("User entered password");
        loginPage.password.sendKeys(password);
        logger.info("User entered password");
    }

    @Und("klickt User Anmelden Button an")
    public void klicktUserAnmeldenButtonAn() {
        new LoginPage().anmelden.click();
        logger.info("User clicked on enter button");
    }

    @Dann("soll Anmeldung erfolgreich sein")
    public void sollAnmeldungErfolgreichSein() {
        LoginPage loginPage = new LoginPage();
        BrowserUtilities.waitForPresenceOfElement(loginPage.willkommen, 5000);
        Assert.assertTrue("Verification of Willkommen", loginPage.willkommen.isDisplayed());
        logger.info("'Willkommen' is verified on mainpage");
    }
}
