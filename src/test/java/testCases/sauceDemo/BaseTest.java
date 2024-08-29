package testCases.sauceDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import utilities.ReadConfig;

public class BaseTest {

    public String baseURL;
//    public String baseURL = "https://www.saucedemo.com/";

    public static WebDriver driver;

    @BeforeClass
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
        driver.get(ReadConfig.getSauceDemoURL());
//        driver.manage().window().maximize();
    }

    public void  goToHomePage() {
        driver.get(ReadConfig.getSauceDemoURL());
    }


    @AfterClass
    public void  tearDown() {
        driver.quit();
    }


}
