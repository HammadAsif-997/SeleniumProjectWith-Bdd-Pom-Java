package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumUtil {
    public static void inputText(WebDriver driver, By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public static void clickElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public static String getText(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        return element.getText();
    }

    public static String getAttributeValue(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        return element.getAttribute("value");
    }

    public static void selectDropdown(WebDriver driver, By locator, String option) {
        WebElement element = driver.findElement(locator);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(option);
    }

    public static void openDropdown(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        Select dropdown = new Select(element);
    }

    public static void ReloadPage(WebDriver driver) {

        driver.get(driver.getCurrentUrl());
    }

    public static void ExplicitWait(WebDriver driver, By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }




//    public static void openFilterDropdown(WebDriver driver, By locator) {
//        WebElement dropdown = driver.findElement(locator);
//        dropdown.click();
//    }
//
//    public static void selectOption(WebDriver driver, By locator, String option) {
//        Select select = new Select(driver.findElement(locator));
//        select.selectByVisibleText(option);
//    }

}
