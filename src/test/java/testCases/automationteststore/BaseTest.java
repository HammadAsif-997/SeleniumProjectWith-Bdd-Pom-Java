package testCases.automationteststore;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.automationteststore.AddItemToCartPage;
import utilities.ReadConfig;
import utilities.SeleniumUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    public String baseURL;
//    public String baseURL = "https://www.saucedemo.com/";

    public static WebDriver driver;
    AddItemToCartPage Ac;

    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional String browser) {
//    public void setUp() {
//        String browser = ReadConfig.getBrowser();
        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else {
            throw new IllegalArgumentException("Invalid browser specified in config");
        }
        driver.manage().window().maximize();
        driver.get(ReadConfig.getAutomationTestStoreUrl());

        SeleniumUtil.clickElement(driver, By.xpath("//*[@id=\"customer_menu_top\"]/li/a"));
        SeleniumUtil.inputText(driver, By.name("loginname"), "hammad");
        SeleniumUtil.inputText(driver, By.name("password"), "Demo@123");
        SeleniumUtil.clickElement(driver, By.xpath("//*[@id=\"loginFrm\"]/fieldset/button"));
        GotoHomePage();
        new AddItemToCartPage(driver).changeCurrencytoDollar();
    }

    public void GotoHomePage(){
        driver.get(ReadConfig.getAutomationTestStoreUrl());
    }


    @AfterClass
    public void  tearDown() {
        driver.quit();
    }


}
