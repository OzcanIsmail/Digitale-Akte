package de.digitale.akte.utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtilities {

  /**
   * Performs a pause
   *
   * @param miliseconds
   */
  public static void waitFor(int miliseconds) {
    try {
      Thread.sleep(miliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * checks that an element is present on the DOM of a page. This does not * necessarily mean that
   * the element is visible.
   *
   * @param element
   * @param time
   */
  public static void waitForPresenceOfElement(WebElement element, long time) {
    new WebDriverWait(MyDriver.get(), time).until(ExpectedConditions.visibilityOf(element));
  }

  /**
   * Waits for provided element to be clickable
   *
   * @param element
   * @param timeout
   * @return
   */
  public static WebElement waitForClickablility(WebElement element, int timeout) {
    WebDriverWait wait = new WebDriverWait(MyDriver.get(), timeout);
    return wait.until(ExpectedConditions.elementToBeClickable(element));
  }

  /**
   * Waits for provided element to be visible
   *
   * @param element
   * @param timeout
   * @return
   */
  public static WebElement waitForVisiblity(WebElement element, int timeout) {
    WebDriverWait wait = new WebDriverWait(MyDriver.get(), timeout);
    return wait.until(ExpectedConditions.visibilityOf(element));
  }

  public static void sentTextWithJS(WebElement myElement, String inputText) {
    String js = "arguments[0].setAttribute('value','" + inputText + "')";
    ((JavascriptExecutor) MyDriver.get()).executeScript(js, myElement);
  }

  /**
   * this method returns date and time as string
   *
   * @return date
   */
  public static final String getDateTime() {
    return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
  }

  public static WebElement ElementWithText(String text) {
    return MyDriver.get().findElement(By.xpath("//*[text()='" + text + "']"));
  }

  /**
   * return a list of string from a list of elements
   *
   * @param list of webelements
   * @return list of string
   */
  public static List<String> getElementsText(List<WebElement> list) {
    List<String> elemTexts = new ArrayList<>();
    for (WebElement el : list) {
      elemTexts.add(el.getText());
    }
    return elemTexts;
  }

  /**
   * This method removes non-Numerics
   *
   * @param list
   * @return
   */
  public static List<String> removeNonNumerics(List<String> list) {
    ArrayList<String> newList = new ArrayList<>();
    for (String s : list) {
      String s1 = s.replaceAll("[\\D]", ""); // replace all non-digits
      newList.add(s1);
    }
    return newList;
  }

  /**
   * This method sort List as Desscending, in case of € sign, remove . and €
   *
   * @param list
   * @return
   */
  public static List<String> sortAscending(List<String> list) {
    List<Integer> listInt = new ArrayList<>();
    // change the values to integer in new array
    if (list.get(0).contains("000")) {
      List<String> newList = removeNonNumerics(list);
      List<String> newList2 = new ArrayList<>();
      for (String str : newList) {
        listInt.add(Integer.valueOf(str));
      }
      Collections.sort(listInt);
      // change the values to String
      for (int num : listInt) {
        newList2.add(Integer.toString(num));
      }
      return newList2;
    } else {
      Collections.sort(list);
      return list;
    }
  }

  /**
   * This method sort List as Descending, in case of € sign, remove . and €
   *
   * @param list
   * @return
   */
  public static List<String> sortDescending(List<String> list) {
    List<Integer> listInt = new ArrayList<>();
    // change the values to integer in new array
    if (list.get(0).contains("000")) {
      List<String> newList = removeNonNumerics(list);
      List<String> newList2 = new ArrayList<>();
      for (String str : newList) {
        listInt.add(Integer.valueOf(str));
      }
      Collections.sort(listInt, Collections.reverseOrder());
      // change the values to String
      for (int num : listInt) {
        newList2.add(Integer.toString(num));
      }
      return newList2;
    } else {
      Collections.sort(list, Collections.reverseOrder());
      return list;
    }
  }

  /**
   * Clicks on an element using JavaScript
   *
   * @param element
   */
  public static void clickWithJS(WebElement element) {
    ((JavascriptExecutor) MyDriver.get())
        .executeScript("arguments[0].scrollIntoView(true);", element);
    ((JavascriptExecutor) MyDriver.get()).executeScript("arguments[0].click();", element);
  }

  /**
   * This method removes null values from list of String
   *
   * @param list
   * @return
   */
  public static List<String> removeNulls(List<String> list) {
    // Removing nulls using List.removeAll()
    // passing a collection with single element "null"
    list.removeAll(Collections.singletonList(null));
    return list;
  }
}
