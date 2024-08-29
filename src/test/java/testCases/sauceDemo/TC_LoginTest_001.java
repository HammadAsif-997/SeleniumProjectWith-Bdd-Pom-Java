package testCases.sauceDemo;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.sauceDemo.CartPage;
import pageObjects.sauceDemo.InventoryPage;
import pageObjects.sauceDemo.LoginPage;

import java.util.List;

public class TC_LoginTest_001 extends BaseTest {

    public String username = "standard_user";
    public String validPassword = "secret_sauce";
    public String invalidPassword = "standard_user";

    @Test(priority = 1)

    public void InvalidPasswordloginTest(){

        LoginPage lp = new LoginPage(driver);
        lp.login(username,invalidPassword);
        Assert.assertEquals(lp.GetErrorMessage(),"Epic sadface: Username and password do not match any user in this service");
//        SeleniumUtil.ReloadPage(driver);
    }

    @Test(priority = 2)
    public void ValidPasswordloginTest(){

        LoginPage lp = new LoginPage(driver);
        lp.login(username,validPassword);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

    }


    @Test(priority = 3)
    public void AllItemsLowToHigh(){
        InventoryPage Ip = new InventoryPage(driver);
        Ip.sortByPriceLowToHigh();
        Assert.assertTrue(Ip.verifyPricesSortedLowToHigh());

    }

    @Test(priority = 4)
    public void QuanityinCart(){
        LoginPage lp = new LoginPage(driver);
        lp.login(username,validPassword);
        CartPage Cart = new CartPage(driver);
        InventoryPage Ip = new InventoryPage(driver);


//        System.out.println(Ip.addTopTwoItemsinCart());
        List<String> itemsAdded = Ip.addTopTwoItemsinCart();
//        Ip.addToCart("add-to-cart-sauce-labs-fleece-jacket");
//        Ip.addToCart("add-to-cart-sauce-labs-backpack");
        Ip.goToCart();

        Assert.assertEquals(Cart.getCartTotalQuantityCount(),itemsAdded.size());

        Assert.assertEquals(Cart.getCartItemCount(),itemsAdded.size());
        Assert.assertTrue(Cart.verifyCartTotal(itemsAdded));

    }



}
