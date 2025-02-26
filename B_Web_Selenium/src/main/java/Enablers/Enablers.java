package Enablers;

import MainClasses.MainClass_BDD;
import org.codehaus.plexus.util.Base64;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Enablers extends MainClass_BDD {
    public WebDriver wdriver;
    public JavascriptExecutor js;

    // Constructor to initialize WebDriver
    public Enablers(WebDriver wdriver) {
        this.wdriver = wdriver;
    }

    // Method to execute JavaScript click on a WebElement
    public void javaScriptExecutor(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    // Method to scroll down the page by 250 pixels
    public void scrollDown() {
        js.executeScript("scroll(0, 250);");
    }

    // Method to scroll up to the top of the page
    public void scrollUp() {
        js.executeScript("window.scrollTo(0,0)");
    }

    // Method to scroll to a specific WebElement
    public void scrollToElement(WebElement element) {
        Coordinates cor = ((Locatable) element).getCoordinates();
        cor.inViewPort();
    }

    // Method to scroll to the end of the page
    public void scrollToEndOfPage() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    // Method to wait until a WebElement is visible
    public WebElement waitForElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(wdriver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    // Method to wait until a WebElement is clickable
    public WebElement waitForElementToBeClickable(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(wdriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        return webElement;
    }

    // Method to introduce a delay in seconds
    public void secondsDelay(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Method to take a screenshot and save it with a timestamp
    public void takeScreenCapture(String screenshotName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
        TakesScreenshot screenshot = (TakesScreenshot) wdriver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/target/TestEvidence/" + screenshotName + timestamp + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);

        try (FileInputStream fileInputStream = new FileInputStream(finalDestination)) {
            byte[] bytes = new byte[(int) finalDestination.length()];
            fileInputStream.read(bytes);
            String encodedBase64 = new String(Base64.encodeBase64(bytes));
            String img = "data:image/png;base64," + encodedBase64;
        }
    }

    // Method to click on a WebElement if it is displayed
    public boolean clickElement(WebElement element) {
        if (element.isDisplayed()) {
            element.click();
            return true;
        }
        return false;
    }

    // Method to clear text from a WebElement if it is displayed
    public boolean clearText(WebElement element) {
        if (element.isDisplayed()) {
            element.clear();
            return true;
        }
        return false;
    }

    // Method to check if a WebElement is displayed
    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    // Method to set implicit wait time
    public void implicitWait(int sec) {
        wdriver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    // Method to generate a random string appended to a given string
    public String generateRandomString(String myString) {
        String alphabets = "abcdefghijklmnopphsgdzyx";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 6;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabets.length());
            char randomchar = alphabets.charAt(index);
            sb.append(randomchar);
        }
        String randomString = sb.toString();
        return myString + randomString;
    }

    // Method to wait until a WebElement is visible
    public void elementShouldBeVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(wdriver, 8);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException e) {
            // log.error("Failed to find the element : " + element);
        }
    }

    // Method to capture test evidence by taking a screenshot
    public void captureTestEvidence(String screenshotName) {
        try {
            takeScreenCapture(screenshotName);
        } catch (IOException e) {
            // log.error("Failed to capture screenshot: " + e.getMessage());
        }
    }
}