package pageObjects.automationteststore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.sauceDemo.BasePage;
import utilities.SeleniumUtil;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class AddItemToCartPageCssSelector extends BasePage {

    public By DropdownFilter = By.cssSelector("#sort");
    public By totalAmount = By.cssSelector("body > div > header > div.container-fluid > div > div.block_7 > ul > li > a > span.cart_total") ;
    public By cartButton = By.cssSelector("body > div > header > div.container-fluid > div > div.block_7 > ul > li > a");
    public By cartQuantity = By.cssSelector("body > div > header > div.container-fluid > div > div.block_7 > ul > li > a > span.label.label-orange.font14");
    public By cartItems = By.cssSelector("#top_cart_product_list > div > table > tbody > tr");
    public By ApparelAndAccessoriesButton = By.cssSelector("#categorymenu > nav > ul > li:nth-child(2) > a");
    public By TShirtsButton = By.cssSelector("#maincontainer > div > div > div > div > ul > li:nth-child(2) > a");
    public By shoesButton = By.cssSelector("#maincontainer > div > div > div > div > ul > li:nth-child(1) > a");
    public By AddToCartButton = By.cssSelector("#product > fieldset > div > ul > li > a");
    public By ProductQuantityField = By.cssSelector("#product_quantity");
    public By ItemNameCartPage = By.cssSelector("#cart > div > div.container-fluid.cart-info.product-list > table > tbody > tr > td:nth-child(2) > a");


    public void gotoApparelAndAccessories(){
        SeleniumUtil.clickElement(driver,ApparelAndAccessoriesButton);
    }

    public void gotoTshirts(){
        SeleniumUtil.clickElement(driver,TShirtsButton);
    }

    public void goToShoes(){
        SeleniumUtil.clickElement(driver,shoesButton);
    }

    public void sortItems(String optionName) {
        SeleniumUtil.selectDropdown(driver,DropdownFilter,optionName);
    }

    public void selectItem(String cssSelector){
        SeleniumUtil.clickElement(driver, By.cssSelector(cssSelector));
    }

    public void selectSize(String sizeCssSelector, String optionName) {
        SeleniumUtil.selectDropdown(driver,By.cssSelector(sizeCssSelector),optionName);
    }

    public void setProductQuantity(String quantity) {
        SeleniumUtil.inputText(driver,ProductQuantityField,quantity);
    }


    public AddItemToCartPageCssSelector(WebDriver driver) {
        super(driver);
    }

    public void addItem(){
        SeleniumUtil.clickElement(driver,AddToCartButton);
    }

    public void gotoCart(){
        SeleniumUtil.clickElement(driver,cartButton);
    }

    public int getCartItemCount(){
        return driver.findElements(cartItems).size();
    }

    public double getTotalCartAmount(){
        String priceText = SeleniumUtil.getText(driver, totalAmount).replace("$","");
        double price = Double.parseDouble(priceText);
        return price;
    }

    public List<String> getItemsInCart(){
//        return SeleniumUtil.getText(driver, By.cssSelector(CartItemCssSelecter));

        List<String> items = driver.findElements(ItemNameCartPage)
                .stream()
                .map(e -> e.getText().replace("$", ""))
                .collect(Collectors.toList());

        return items;
    }

    public int getCartQuantity(){
        return parseInt(SeleniumUtil.getText(driver, cartQuantity));

    }






}
