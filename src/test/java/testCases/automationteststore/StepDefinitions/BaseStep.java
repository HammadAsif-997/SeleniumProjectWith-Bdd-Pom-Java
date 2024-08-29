package testCases.automationteststore.StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pageObjects.automationteststore.AddItemToCartPage;
import utilities.ReadConfig;
import utilities.SeleniumUtil;

public class BaseStep {
    public String baseURL;
//    public String baseURL = "https://www.saucedemo.com/";

    public static WebDriver driver;

    @Before
    public void setUp() {
        String browser = ReadConfig.getBrowser();
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

    }

    public void GotoHomePage(){
        driver.get(ReadConfig.getAutomationTestStoreUrl());
    }


    @After
    public void  tearDown() {
        driver.quit();
    }
}
