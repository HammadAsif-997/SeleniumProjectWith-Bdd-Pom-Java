package pageObjects.sauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.SeleniumUtil;

public class LoginPage extends BasePage {

//    WebDriver driver;

    public By usernameField =  By.id("user-name");
    public By passwordField = By.id("password");
    public By loginButton = By.id("login-button");
    public By errorMessage  = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3");

    public LoginPage(WebDriver driver) {
        super(driver);
    }


//    public void login(String username, String password) {
//        driver.findElement(usernameField).sendKeys(username);
//        driver.findElement(passwordField).sendKeys(password);
//        driver.findElement(loginButton).click();
//    }


    public void login(String username, String password) {

        SeleniumUtil.inputText(driver,usernameField,username);
        SeleniumUtil.inputText(driver,passwordField,password);
        SeleniumUtil.clickElement(driver,loginButton);
    }


//    public String GetErrorMessage() {
//        return driver.findElement(errorMessage).getText();
//    }
    public String GetErrorMessage() {
        SeleniumUtil.getText(driver,loginButton);
        return driver.findElement(errorMessage).getText();
    }

//
//    public String GetPageTitle() {
//        return driver.findElement(By.id(LocatorsPage.pageTitle)).getText();
////        return pageTitle.getText();
//    }




}
