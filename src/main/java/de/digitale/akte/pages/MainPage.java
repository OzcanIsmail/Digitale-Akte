package de.digitale.akte.pages;

import de.digitale.akte.utilities.MyDriver;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends LoginPage {

  public WebElement getElementwithFileName(String filename) {
    return MyDriver.get()
        .findElement(
            By.xpath("//div[@class='nw-renditionlist-element' and text()='Seite 1']/../../.."));
  }

  @FindBy(xpath = "//span[@class='fileDropTitleLarge']")
  public WebElement dokumentErstellen;

  public static String readPdfContent(String url) throws IOException {

    URL pdfUrl = new URL(url);
    InputStream in = pdfUrl.openStream();
    BufferedInputStream bf = new BufferedInputStream(in);
    PDDocument doc = PDDocument.load(bf);
    int numberOfPages = getPageCount(doc);
    System.out.println("The total number of pages " + numberOfPages);
    String content = new PDFTextStripper().getText(doc);
    doc.close();

    return content;
  }

  public static int getPageCount(PDDocument doc) {
    // get the total number of pages in the pdf document
    int pageCount = doc.getNumberOfPages();
    return pageCount;
  }
}
