package pageObjects.automationteststore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.sauceDemo.BasePage;
import utilities.SeleniumUtil;

import java.time.Duration;
import java.util.List;

import static com.google.common.base.Ascii.toUpperCase;
import static java.lang.Integer.parseInt;

public class AddItemToCartPage1 extends BasePage {

    public By DropdownFilter = By.xpath("//select[@id=\"sort\"]");
    public By totalAmount = By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a/span[2]") ;
    public By cartButton = By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a");
//    public By cartQuantity = By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a/span[1]");
    public By cartQuantity = By.xpath("//ul[@class=\"nav topcart pull-left\"]/li/a/span[1]");
    public By cartItems = By.xpath("//*[@id=\"top_cart_product_list\"]/div/table/tbody/tr");
//    public By skinCareButton = By.xpath("//*[@id=\"categorymenu\"]/nav/ul/li[4]/a");
    public By skinCareButton = By.xpath("//nav/ul/li[4]/a");
    public By menButton = By.xpath("//nav/ul/li[6]/a");
    public By saleItemBadge = By.xpath("//div[2]/span[@class=\"sale\"]");
//    public By cartPageItems = By.cssSelector("#top_cart_product_list > div > table > tbody > tr");
//    public By saleItemBadge = By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div/div[2]/span");
//    public By saleItemBadge = By.xpath("//span[@class=\"sale\"]");
    public By outOfStockItem = By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div[1]/div/div[3]/span");
//    public By allItems = By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div/div[2]");
//    public By allItems = By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div[@class=\"col-md-3 col-sm-6 col-xs-12\"]//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div[@class=\"col-md-3 col-sm-6 col-xs-12\"]");
    public By allItemsOnProductPage = By.xpath("//div[contains(@class, 'col-md-3 col')]");
    public By allItemsOnCartPage = By.xpath("//*[@id=\"cart\"]/div/div[1]/table/tbody/tr");
    public By saleBadge = By.xpath(".//div[2]/span");
//    public By outOfStockItemBadge = By.xpath(".//div[2]/div[3]/span[@class=\"nostock\"]");
    public By outOfStockItemBadge = By.xpath(".//span[@class=\"nostock\"]");
    public By AddToCartButton = By.xpath("//ul[@class=\"productpagecart\"]/li/a");
    public By productTitle = By.xpath(".//div[1]/div/a");
    public By selectProduct = By.xpath(".//div/a/img");
    public By itemNameOnCart = By.xpath(".//td[2]/a");
    public By itemPriceOnCart = By.xpath(".//td[6]");
    public By deleteButtonOnCart = By. xpath("//*[@id=\"cart\"]/div/div[1]/table/tbody/tr/td[7]/a");


    //    public By items = By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div/div[2]");

    public AddItemToCartPage1(WebDriver driver) {
        super(driver);
    }

    public void gotoSkinCare(){
        SeleniumUtil.clickElement(driver,skinCareButton);
    }


    public void gotoMen(){
        SeleniumUtil.clickElement(driver,menButton);
    }

    public int getSaleItemsCount(){
//       System.out.println(driver.findElements(saleItemBadge).size());
        return driver.findElements(saleItemBadge).size(); // Assuming sale items have class "sale"
    }

    public void deleteItemsFromCart() {
        gotoCart();
        // Flag to track whether it's the first iteration
        boolean isFirstIteration = true;

        List<WebElement> allItems = driver.findElements(allItemsOnCartPage);
        if (!allItems.isEmpty()){
            for (WebElement item : allItems) {

                if (isFirstIteration) {
                    isFirstIteration = false;

                    continue; // Skip the first iteration

                } else {
//                    System.out.println(item);
//                    List<WebElement> secondaryElements = item.findElements(deleteButtonOnCart);
//                    SeleniumUtil.clickElement(driver,deleteButtonOnCart);
//                    if (!item.findElements(By. xpath("//*[@id=\"cart\"]/div/div[1]/table/tbody/tr["+iteration+"]/td[7]/a")).isEmpty()) {

                    if (!(driver.findElements(By. xpath("//*[@id=\"cart\"]/div/div[1]/table/tbody/tr/td[7]/a")).isEmpty())) {
//                    if (item.findElements(By. xpath("./td[7]/a")).size() > 1) {
                            driver.findElement(By.xpath("(//*[@id=\"cart\"]/div/div[1]/table/tbody/tr/td[7]/a)[1]"));
                            item.findElement(By.xpath("./td[7]/a")).click();
                        allItems = driver.findElements(allItemsOnCartPage);
//                        item.findElement(deleteButtonOnCart).click();
                    }
                    else {
                        driver.findElements(allItemsOnCartPage);
                        item.findElement(deleteButtonOnCart).click();
                    }
//                deleteItemsFromCart();

                }

            }
        }
    }




    public void getSaleAndOutOfStockItemsCount(){

        List<WebElement> allItems = driver.findElements(this.allItemsOnProductPage); // Assuming all items have class "item"

        // Count the number of sale items and out-of-stock items
        int saleItemCount = 0;
        int outOfStockItemCount = 0;

        for (WebElement item : allItems) {
//            List<WebElement> saleItems = item.findElements(By.className("sale"));
            List<WebElement> saleItems = item.findElements(saleBadge);
            if (!saleItems.isEmpty()) {
                saleItemCount++;

//                List<WebElement> outOfStockLabel = item.findElements(By.className("nostock"));
//                List<WebElement> outOfStockLabel = item.findElements(By.xpath(".//div[2]/div[3]/span[@class=\"nostock\"]"));
                List<WebElement> outOfStockLabel = item.findElements(outOfStockItemBadge);
                if (!outOfStockLabel.isEmpty()) {
                    outOfStockItemCount++;
                }
            }
        }

        // Log how many items are on sale and how many are out of stock
        System.out.println("Number of items on sale: " + saleItemCount);
        System.out.println("Number of out-of-stock items in sale: " + outOfStockItemCount);

    }

    public List<String> addSaleAndInStockItems(){

        List<WebElement> allItems = driver.findElements(this.allItemsOnProductPage); // Assuming all items have class "item"

        // Count the number of sale items and out-of-stock items
        int saleItemCount = 0;
        int outOfStockItemCount = 0;
        List<String> addedProducts = new java.util.ArrayList<>(List.of());

        for (WebElement item : allItems) {
            List<WebElement> saleItems = item.findElements(saleBadge);
            if (!saleItems.isEmpty()) {
                saleItemCount++;
                List<WebElement> outOfStockLabel = item.findElements(outOfStockItemBadge);
                if (!outOfStockLabel.isEmpty()) {
                    outOfStockItemCount++;
                }
                else{
                    item.findElement(By.className("productcart")).click();
                    addedProducts.add(item.findElement(productTitle).getText());
                }
            }
        }

        return addedProducts;


    }


    public List<String> addNewestItem(){

        List<WebElement> allItems = driver.findElements(this.allItemsOnProductPage); // Assuming all items have class "item"

        // Count the number of sale items and out-of-stock items
        int saleItemCount = 0;
        int outOfStockItemCount = 0;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<String> addedProducts = new java.util.ArrayList<>(List.of());
//        String itemName = "";

        for (WebElement item : allItems) {
//            WebElement productNameElement = wait.until(ExpectedConditions.visibilityOf(item.findElement(By.className("prdocutname"))));

            String itemName = toUpperCase(item.findElement(productTitle).getText());
//            String itemName = productNameElement.getText().toUpperCase();

            List<WebElement> outOfStockLabel = item.findElements(outOfStockItemBadge);
            if (outOfStockLabel.isEmpty()) {
                item.findElement(selectProduct).click();
                SeleniumUtil.clickElement(driver,AddToCartButton);
                addedProducts.add(itemName);
                break;
            }

        }
        return addedProducts;
    }


//    By elementLocator = By.className("prdocutname");
    public List<String> addItemsEndWithM(){

        List<WebElement> allItems = driver.findElements(this.allItemsOnProductPage); // Assuming all items have class "item"

        // Count the number of sale items and out-of-stock items
        int saleItemCount = 0;
        int outOfStockItemCount = 0;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<String> addedProducts = new java.util.ArrayList<>(List.of());
//        String itemName = "";



        for (WebElement item : allItems) {
//            WebElement productNameElement = wait.until(ExpectedConditions.visibilityOf(item.findElement(By.className("prdocutname"))));

            String itemName = toUpperCase(item.findElement(productTitle).getText());
//            String itemName = productNameElement.getText().toUpperCase();

            if (itemName.endsWith("M") && !addedProducts.contains(itemName)) {
                List<WebElement> outOfStockLabel = item.findElements(outOfStockItemBadge);
                if (outOfStockLabel.isEmpty()) {
                    item.findElement(selectProduct).click();
                    SeleniumUtil.clickElement(driver,AddToCartButton);
                    addedProducts.add(itemName);
//                    driver.navigate().back();
//                    driver.navigate().back();

//                    allItems = driver.findElements(this.allItemsOnProductPage);
                    break;

                }
            }
        }
        return addedProducts;
    }

    public double getSaleItemsAmountFromCartPage(List<String> names){
        gotoCart();
        double price = 0.0;
        String saleItems = "";

        // Flag to track whether it's the first iteration
        boolean isFirstIteration = true;

        List<WebElement> allItems = driver.findElements(allItemsOnCartPage);
        for (WebElement item : allItems) {
            if (isFirstIteration) {
                isFirstIteration = false;
                continue; // Skip the first iteration
            }else {

                saleItems = item.findElement(itemNameOnCart).getText();

                if (names.contains(toUpperCase(saleItems))) {

                    String priceText = item.findElement(itemPriceOnCart).getText().replace("$","");
                    price += Double.parseDouble(priceText);

                }

            }
        }
        return price;
    }

    public List<String> getItemsFromCartPageEndWithM(){
        gotoCart();
        double price = 0.0;
        String itemName = "";
        List<String> cartProducts = new java.util.ArrayList<>(List.of());


        // Flag to track whether it's the first iteration
        boolean isFirstIteration = true;

        List<WebElement> allItems = driver.findElements(allItemsOnCartPage);
        for (WebElement item : allItems) {
            if (isFirstIteration) {
                isFirstIteration = false;
                continue; // Skip the first iteration
            }else {

                itemName = item.findElement(itemNameOnCart).getText();

                if (toUpperCase(itemName).endsWith("M")) {
                    cartProducts.add(toUpperCase(itemName));
                }

            }
        }
        return cartProducts;
    }

    public void selectBrand(String xpath) {
        SeleniumUtil.clickElement(driver,By.xpath(xpath));
    }

    public void sortItems(String optionName) {
        SeleniumUtil.selectDropdown(driver,DropdownFilter,optionName);
    }

    public void addItem(String xpath){
        SeleniumUtil.clickElement(driver,By.xpath(xpath));
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

    public int getCartQuantity(){
//        return SeleniumUtil.getText(driver,CartQuantity);
        return parseInt(SeleniumUtil.getText(driver, cartQuantity));
    }









}
