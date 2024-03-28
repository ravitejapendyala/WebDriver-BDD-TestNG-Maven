package com.sample.utilities;

import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

public class Waits {


    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));

    }

    public static WebElement waitForVisibility(WebDriver driver, WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));

    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickability(WebDriver driver,WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickability(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
        }
    }

    public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(timeinsec))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
        return element;
    }

    public static void waitFixedTime(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception ignored) {
        }
    }

    /**
     * Waits for element to be not stale
     *
     * @param element
     */
    public static void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            try {
                element.isDisplayed();
                break;
            } catch (StaleElementReferenceException st) {
                y++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (WebDriverException we) {
                y++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void waitFixTime(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ignored) {
        }
    }

    public static void waitForLoader() {

        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(120));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'invisible-when-ready')]")));

        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void waitForBlocker() {

        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(120));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Loading')]")));

        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void waitForPresence(String xpath, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        //return wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }
    public static void waitForElementToBeDisplayed(WebElement element, int timeout) throws Exception {
        for(int i = 0; i < 3; i++){
            if(element.isDisplayed()){
                break;
            }else if(i ==3){
                throw new Exception("Element is not displayed after 3 tries.");
            }else{
                continue;
            }
        }
    }
    public static void waitForElementToDisappear(By locator, int timeout) throws Exception {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    public static void waitForElementsText(WebElement element, String textToBe){
        for(int i = 0; i < 3; i++){
            if(element.isDisplayed() && element.getText().equals(textToBe)){
                break;
            }else{
                continue;
            }
        }
    }

//    static void waitForPageLoad(WebDriver Driver.getDriver()) {
//        Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {
//
//            @Override
//            public boolean apply(WebDriver input) {
//                return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
//            }
//
//        };
//        wait.until(pageLoaded);
//    }

//    public static void WaitUntilDocumentIsReady( WebDriver driver, int timeoutInSeconds) {
//        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(120));
//
//        try {
//            Func<WebDriver, bool> readyCondition = webDriver => (bool)jse.executeScript("return (document.readyState == 'complete' && jQuery.active == 0)");
//            wait.Until(readyCondition);
//        } catch(InvalidOperationException) {
//            wait.Until(wd => jse.executeScript("return document.readyState").ToString() == "complete");
//        }
//    }

    public void waitForPageLoad(WebDriver driver, int timeout) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver driver) {

                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");}

        };
        wait.until(pageLoadCondition);

    }
}
