package pageObjects.automationteststore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.sauceDemo.BasePage;
import utilities.SeleniumUtil;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Ascii.toUpperCase;
import static java.lang.Integer.parseInt;

public class AddItemToCartPage extends BasePage {

    public By DropdownFilter = By.xpath("//select[@id=\"sort\"]");
    public By totalAmount = By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a/span[2]") ;
    public By cartButton = By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a");
//    public By cartQuantity = By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a/span[1]");
    public By cartQuantity = By.xpath("//ul[@class=\"nav topcart pull-left\"]/li/a/span[1]");
    public By cartItems = By.xpath("//*[@id=\"top_cart_product_list\"]/div/table/tbody/tr");
//    public By skinCareButton = By.xpath("//*[@id=\"categorymenu\"]/nav/ul/li[4]/a");
    public By skinCareButton = By.xpath("//nav/ul/li[4]/a");
    public By menButton = By.xpath("//nav/ul/li[6]/a");
    public By makeupButton = By.xpath("//nav/ul/li[3]/a");
    public By saleItemBadge = By.xpath("//div[2]/span[@class=\"sale\"]");
    public By outOfStockItem = By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div[1]/div/div[3]/span");
    public By allItemsOnProductPage = By.xpath("//div[contains(@class, 'col-md-3 col')]");
    public By allSubCategoryOnProductPage = By.xpath("//ul[@class=\"thumbnails row\"]/li");
    public By allItemsOnCartPage = By.xpath("//*[@id=\"cart\"]/div/div[1]/table/tbody/tr");
    public By saleBadge = By.xpath(".//div[2]/span");
//    public By outOfStockItemBadge = By.xpath(".//div[2]/div[3]/span[@class=\"nostock\"]");
    public By outOfStockItemBadge = By.xpath(".//span[@class=\"nostock\"]");
    public By callToOrderItemBadge = By.xpath(".//a[@class=\"btn call_to_order\"]");
    public By AddToCartButton = By.xpath("//ul[@class=\"productpagecart\"]/li/a");
    public By productTitle = By.xpath(".//div[1]/div/a");
//    public By ItemTotalPriceOnProductPage = By.xpath("//span[@class=\"total-price\"]");
    public By ItemTotalPriceOnProductPage = By.className("total-price");
    public By selectProduct = By.xpath(".//div/a/img");
    public By itemNameOnCart = By.xpath(".//td[2]/a");
    public By itemPriceOnCart = By.xpath(".//td[6]");
    public By deleteButtonOnCart = By. xpath("//*[@id=\"cart\"]/div/div[1]/table/tbody/tr/td[7]/a");
    public By CurrencyDropdown = By.xpath("//ul[@class=\"nav language pull-left\"]/li");
    public By CurrencySelectedText = By.xpath("//ul[@class=\"nav language pull-left\"]//span/span");
    public By dollarCurrency = By. xpath("//a[text()='$ US Dollar']");
    public By poundCurrency = By. xpath("//a[text()='£ Pound Sterling']");
    public By euroCurrency = By. xpath("//ul[@class=\"dropdown-menu currency\"]/li[1]/a");


    //    public By items = By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/div[3]/div/div[2]");

    public AddItemToCartPage(WebDriver driver) {
        super(driver);
    }

    public void gotoSkinCare(){
        SeleniumUtil.clickElement(driver,skinCareButton);
    }


    public void gotoMen(){
        SeleniumUtil.clickElement(driver,menButton);
    }

    public void gotoMakeup(){
        SeleniumUtil.clickElement(driver,makeupButton);
    }
    public List<Double> goToAllMakeupSubPagesAndAddSaleItems(){

        gotoMakeup();
        List<WebElement> allSubCat = driver.findElements(allSubCategoryOnProductPage); // Assuming all items have class "item"

        // Count the number of sale items and out-of-stock items
        List<Double> addedProducts = new ArrayList<>(List.of());
        List<WebElement> allNewSubCat;
        for(int i = 0; i < allSubCat.size(); i++){
            allNewSubCat = driver.findElements(allSubCategoryOnProductPage);
            allNewSubCat.get(i).findElement(By.xpath(".//a")).click();
            addedProducts.addAll(addAllSaleAndInStockItems());
            gotoMakeup();
        }

        return addedProducts;
    }

    public List<Double> goToAllMakeupSubPagesAndAddNonSaleItemsUntil15(){
        List<Double> addedProducts = new ArrayList<>(List.of());
        if(getItemsCountFromCart() < 15){
            gotoMakeup();
            List<WebElement> allSubCat = driver.findElements(allSubCategoryOnProductPage); // Assuming all items have class "item"
            List<WebElement> allNewSubCat;
            for(int i = 0; i < allSubCat.size(); i++){
                if(getItemsCountFromCart() >= 15){
                    return addedProducts;
                }
                driver.navigate().back();
                allNewSubCat = driver.findElements(allSubCategoryOnProductPage);
                allNewSubCat.get(i).findElement(By.xpath(".//a")).click();
                addedProducts.addAll(addAllNonSaleAndInStockItemsUntil15());
                gotoMakeup();
            }
        }
        return addedProducts;
    }

    public List<Double> goToAllMenSubPagesAndAddNonSaleItemsUntil15(){
        List<Double> addedProducts = new ArrayList<>(List.of());
        if(getItemsCountFromCart() < 15){
            gotoMen();
            List<WebElement> allSubCat = driver.findElements(allSubCategoryOnProductPage); // Assuming all items have class "item"

            // Count the number of sale items and out-of-stock items

            List<WebElement> allNewSubCat;
            for(int i = 0; i < allSubCat.size(); i++){
                if(getItemsCountFromCart() >= 15){
                    return addedProducts;
                }
                driver.navigate().back();
                allNewSubCat = driver.findElements(allSubCategoryOnProductPage);
                allNewSubCat.get(i).findElement(By.xpath(".//a")).click();
                addedProducts.addAll(addAllNonSaleAndInStockItemsUntil15());
                gotoMen();
            }
        }


        return addedProducts;
    }

    public List<Double> goToAllMenSubPagesAndAddSaleItems(){

        gotoMen();
        List<WebElement> allSubCat = driver.findElements(allSubCategoryOnProductPage); // Assuming all items have class "item"

        // Count the number of sale items and out-of-stock items
        List<Double> addedProducts = new ArrayList<>(List.of());
        List<WebElement> allNewSubCat;
        for(int i = 0; i < allSubCat.size(); i++){
            allNewSubCat = driver.findElements(allSubCategoryOnProductPage);
            allNewSubCat.get(i).findElement(By.xpath(".//a")).click();
            addedProducts.addAll(addAllSaleAndInStockItems());
            gotoMen();
        }

        return addedProducts;
    }


    public void gotoMenSubPage(String xpath){
        SeleniumUtil.clickElement(driver,By.xpath("//nav/ul/li[6]/a"));
        SeleniumUtil.clickElement(driver,By.xpath(xpath));
    }

    public int getSaleItemsCount(){
       System.out.println(driver.findElements(saleItemBadge).size());
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
                } else {
                    SeleniumUtil.clickElement(driver,deleteButtonOnCart);
                }

            }
        }
    }

    public int getItemsCountFromCart() {
        gotoCart();
        List<WebElement> allItems = driver.findElements(allItemsOnCartPage);
        return (allItems.size())-1;

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
        List<String> addedProducts = new ArrayList<>(List.of());

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


    public List<Double> addAllSaleAndInStockItems(){

        List<WebElement> allItems = driver.findElements(this.allItemsOnProductPage); // Assuming all items have class "item"

        // Count the number of sale items and out-of-stock items
        List<Double> addedProducts = new ArrayList<>(List.of());
        List<WebElement> allnewItems;
        for(int i = 0; i < allItems.size(); i++){
            allnewItems = driver.findElements(this.allItemsOnProductPage);
            allnewItems.get(i);

            List<WebElement> saleItems = allnewItems.get(i).findElements(saleBadge);

            if (!saleItems.isEmpty()) {

                List<WebElement> outOfStockLabel = allnewItems.get(i).findElements(outOfStockItemBadge);
                if (outOfStockLabel.isEmpty()) {
                    allnewItems.get(i).findElement(selectProduct).click();
                    double itemPrice = 0.00;
                    SeleniumUtil.ExplicitWait(driver,ItemTotalPriceOnProductPage);
                    if(checkCurrency().equals("€")) {
                        itemPrice = Double.parseDouble(SeleniumUtil.getText(driver, ItemTotalPriceOnProductPage).replace("€",""));
                    }
                    else if(checkCurrency().equals("£")) {
                        itemPrice = Double.parseDouble(SeleniumUtil.getText(driver, ItemTotalPriceOnProductPage).replace("£",""));
                    }
                    else if(checkCurrency().equals("$")){
                        itemPrice = Double.parseDouble(SeleniumUtil.getText(driver, ItemTotalPriceOnProductPage).replace("$",""));
                    }
                    SeleniumUtil.clickElement(driver,AddToCartButton);
                    addedProducts.add(itemPrice);
                    driver.navigate().back();
                    driver.navigate().back();

                    allnewItems = driver.findElements(this.allItemsOnProductPage);
                }
            }
        }
        return addedProducts;
    }


    public List<Double> addAllNonSaleAndInStockItemsUntil15(){

        List<WebElement> allItems = driver.findElements(this.allItemsOnProductPage); // Assuming all items have class "item"

        // Count the number of sale items and out-of-stock items
        List<Double> addedProducts = new ArrayList<>(List.of());
        List<WebElement> allnewItems;
        for(int i = 0; i < allItems.size(); i++){
            if(getItemsCountFromCart() >= 15){
                return addedProducts;
            }
            driver.navigate().back();
            allnewItems = driver.findElements(this.allItemsOnProductPage);
            allnewItems.get(i);

            List<WebElement> saleItems = allnewItems.get(i).findElements(saleBadge);
            List<WebElement> calltoOrderLabel = allnewItems.get(i).findElements(callToOrderItemBadge);
            if(calltoOrderLabel.isEmpty()){
                if (saleItems.isEmpty()) {

                    List<WebElement> outOfStockLabel = allnewItems.get(i).findElements(outOfStockItemBadge);
                    if (outOfStockLabel.isEmpty()) {

                        allnewItems.get(i).findElement(selectProduct).click();
                        double itemPrice = 0.00;
                        SeleniumUtil.ExplicitWait(driver,ItemTotalPriceOnProductPage);
                        if(checkCurrency().equals("€")) {
                            itemPrice = Double.parseDouble(SeleniumUtil.getText(driver, ItemTotalPriceOnProductPage).replace("€",""));
                        }
                        else if(checkCurrency().equals("£")) {
                            itemPrice = Double.parseDouble(SeleniumUtil.getText(driver, ItemTotalPriceOnProductPage).replace("£",""));
                        }
                        else if(checkCurrency().equals("$")){
                            itemPrice = Double.parseDouble(SeleniumUtil.getText(driver, ItemTotalPriceOnProductPage).replace("$",""));
                        }
                        SeleniumUtil.clickElement(driver,AddToCartButton);
                        addedProducts.add(itemPrice);
                        driver.navigate().back();
                        driver.navigate().back();

                        allnewItems = driver.findElements(this.allItemsOnProductPage);
                    }
                }
            }

        }
        return addedProducts;
    }

    private String checkCurrency() {
        return SeleniumUtil.getText(driver,CurrencySelectedText);
    }

    public List<String> addNewestItem(){

        List<WebElement> allItems = driver.findElements(this.allItemsOnProductPage); // Assuming all items have class "item"

        // Count the number of sale items and out-of-stock items
        int saleItemCount = 0;
        int outOfStockItemCount = 0;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<String> addedProducts = new ArrayList<>(List.of());
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
        List<String> addedProducts = new ArrayList<>(List.of());
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
        List<String> cartProducts = new ArrayList<>(List.of());


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

    public void changeCurrencytoDollar() {
        SeleniumUtil.clickElement(driver,CurrencyDropdown);
        SeleniumUtil.clickElement(driver,dollarCurrency);
    }
    public void changeCurrencytoPound() {
        SeleniumUtil.clickElement(driver,CurrencyDropdown);
        SeleniumUtil.clickElement(driver,poundCurrency);
    }
    public void changeCurrencytoEuro() {
        SeleniumUtil.clickElement(driver,CurrencyDropdown);
        SeleniumUtil.clickElement(driver,euroCurrency);
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
        Double price = 0.00;

        if(checkCurrency().equals("€")) {
            price = Double.parseDouble(SeleniumUtil.getText(driver, totalAmount).replace("€",""));
        }
        else if(checkCurrency().equals("£")) {
            price = Double.parseDouble(SeleniumUtil.getText(driver, totalAmount).replace("£",""));
        }
        else if(checkCurrency().equals("$")){
            price = Double.parseDouble(SeleniumUtil.getText(driver, totalAmount).replace("$",""));
        }
         
        return price;
    }

    public int getCartQuantity(){
//        return SeleniumUtil.getText(driver,CartQuantity);
        return parseInt(SeleniumUtil.getText(driver, cartQuantity));
    }

    public Double totalSum(List<Double> addedProducts) {
        Double total = 0.0;
        for(Double price : addedProducts){
            total += price;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        Double formattedSum = Double.parseDouble(df.format(total));
        return formattedSum;
    }
}
