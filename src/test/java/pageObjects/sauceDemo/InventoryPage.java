package pageObjects.sauceDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.SeleniumUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    public By sortDropDown =  By.className("product_sort_container");
    public By inventoryItems = By.cssSelector(".inventory_item_price");
    public By cartButton = By.className("shopping_cart_link");
    public By optionDropdown = By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[1]");
    public By FleeJacketAddtoCartButton = By.id("add-to-cart-sauce-labs-fleece-jacket");
    public By backpackAddtoCartButton = By.id("add-to-cart-sauce-labs-backpack");
    public By allItemsOnProductPage = By.xpath("//div[@class=\"inventory_item\"]");
    public By addToCartButton = By.xpath("//*[@class=\"btn btn_primary btn_small btn_inventory \"]");
    public By itemPriceonPage = By.xpath(".//div[@class=\"inventory_item_price\"]");
//    public By itemNameonPage = By.xpath("//div[@data-test=\"inventory-item-name\"]");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void sortByPriceLowToHigh() {

//        SeleniumUtil.selectDropdown(driver,sortDropDown);
        SeleniumUtil.selectDropdown(driver,sortDropDown,"Price (low to high)");

        // Logic to select "low to high" option from dropdown
    }




    public boolean verifyPricesSortedLowToHigh() {
        List<Double> prices = driver.findElements(inventoryItems)
                .stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());

        for (int i = 0; i < prices.size() - 1; i++) {
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public List<String> addTopTwoItemsinCart(){

        List<WebElement> allItems = driver.findElements(this.allItemsOnProductPage); // Assuming all items have class "item"

        // Count the number of sale items and out-of-stock items
        int saleItemCount = 0;
        int outOfStockItemCount = 0;
        int itemsAdded = 1;

//        ArrayList<String> addedProducts = new ArrayList<>();
        List<String> addedProducts = new ArrayList<>(List.of());

        for (WebElement item : allItems) {
            if (itemsAdded <= 2) {
                item.findElement(addToCartButton).click();
                String itemPrice = item.findElement(itemPriceonPage).getText().replace("$", "");
//                String itemName = item.findElement(itemNameonPage).getText();
//                addedProducts.add(itemName);
                addedProducts.add(itemPrice);
                itemsAdded++;
            }
            else {
                break;
            }

        }

        return addedProducts;

    }


    public void addToCart(String id) {
        SeleniumUtil.clickElement(driver,By.id(id));
        // Logic to add specified number of items to cart
    }


    public void goToCart(){
        SeleniumUtil.clickElement(driver,cartButton);
    }


}

