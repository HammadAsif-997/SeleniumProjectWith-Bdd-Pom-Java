package pageObjects.sauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.SeleniumUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class CartPage extends BasePage {

    private By cartItems = By.className("cart_item");
    private By cartQuantity = By.className("shopping_cart_badge");
    private By itemPrices = By.className("inventory_item_price");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartTotalQuantityCount() {
        return parseInt(SeleniumUtil.getText(driver,cartQuantity));

    }


    public boolean verifyCartTotal(List<String> addedPricelist) {

        List<WebElement> items = driver.findElements(cartItems);
        double cartTotal = 0;
        double addedPrice = 0;
        boolean IsPriceEqual = false;

        // Calculate the total price of cart
        for (WebElement item : items) {
            String priceText = item.findElement(itemPrices).getText().replace("$", "");
            cartTotal += Double.parseDouble(priceText);
        }

        // Calculate the total price of added items

        for(String Addedpricelist : addedPricelist) {
            addedPrice += Double.parseDouble(Addedpricelist);
        }

        if (addedPrice == cartTotal){
            IsPriceEqual = true;
        }
        return IsPriceEqual;
    }



    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }







}
