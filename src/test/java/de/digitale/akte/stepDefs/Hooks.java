package de.digitale.akte.stepDefs;

import de.digitale.akte.utilities.BrowserUtilities;
import de.digitale.akte.utilities.MyDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

  @Before
  public void setUp() {
    System.out.println("****BEFORE method starts here!***");
    MyDriver.get().manage().window().maximize();
    MyDriver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @After
  public void tearDown(Scenario scenario) {
    System.out.println("****AFTER method starts here!***");
    if (scenario.isFailed()) {
      final byte[] screenshot =
          ((TakesScreenshot) MyDriver.get()).getScreenshotAs(OutputType.BYTES);
      scenario.attach(
          screenshot,
          "image/png",
          "screenshot_" + MyDriver.get().getTitle() + "_" + BrowserUtilities.getDateTime());
    }

    MyDriver.closeDriver();
  }
}
