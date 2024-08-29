package testCases.automationteststore;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.automationteststore.AddItemToCartPage;
import pageObjects.automationteststore.AddItemToCartPageCssSelector;

import java.util.ArrayList;
import java.util.List;

public class TC_001 extends BaseTest {

    AddItemToCartPage Ac;
    AddItemToCartPageCssSelector Acs;

    @Test(priority = 1)
    public void AddNewDoveItemAndVerify(){

        Ac = new AddItemToCartPage(driver);
        Ac.deleteItemsFromCart();
        //Select Dove Brand
        GotoHomePage();
        Ac.selectBrand("//ul[@id=\"brandcarousal\"]/li[7]");
        Ac.sortItems("Date Old > New");

        Ac.addNewestItem();

        Assert.assertEquals(Ac.getCartItemCount(),1);
        Assert.assertEquals(Ac.getCartQuantity(),1);
        Assert.assertEquals(Ac.getTotalCartAmount(),7.2);
    }

    @Test(priority = 2)
    public void AddHighestPriceTShirtLowestPriceShoesAndVerify(){
        Ac = new AddItemToCartPage(driver);
        Acs = new AddItemToCartPageCssSelector(driver);
        Ac.deleteItemsFromCart();
        Acs.gotoApparelAndAccessories();
        Acs.gotoTshirts();
        Acs.sortItems("Price Low > High");
        Acs.selectItem("div:nth-child(3) > div.thumbnail > a > img");
        Acs.addItem();

        //Repeat whole scenario to add 2nd item.
        Acs.gotoApparelAndAccessories();
        Acs.gotoTshirts();
        Acs.sortItems("Price Low > High");
        //Select Shirt
        Acs.selectItem("div:nth-child(6) > div.thumbnail > a > img");
        Acs.selectSize("#option351", "Medium");
        Acs.addItem();



        //Repeat whole scenario to add Shoes item.
        Acs.gotoApparelAndAccessories();
        Acs.goToShoes();
        Acs.sortItems("Price High > Low");
        //Select Shoes
        Acs.selectItem("div:nth-child(1) > div.thumbnail > a > img");
        Acs.setProductQuantity("2");
        Acs.addItem();


    }


    @Test(priority = 3)
    public void Scenario3(){
        Ac = new AddItemToCartPage(driver);
        Ac.deleteItemsFromCart();
        Ac.gotoSkinCare();
        Ac.getSaleAndOutOfStockItemsCount();
        List<String> itemNames = Ac.addSaleAndInStockItems();
        Ac.gotoCart();
        Assert.assertEquals(Ac.getSaleItemsAmountFromCartPage(itemNames),309.0);
        Assert.assertEquals(Ac.getCartItemCount(),2);
//        Assert.assertEquals(Ac.getTotalCartAmount(),89.00+220.00);

    }

    @Test(priority = 4)
    public void Scenario4(){
        Ac = new AddItemToCartPage(driver);
        Ac.deleteItemsFromCart();
        Ac.gotoMen();
        List<String> addedItems = Ac.addItemsEndWithM();
        List<String> itemsInCartwithM = Ac.getItemsFromCartPageEndWithM();
        Assert.assertEquals(itemsInCartwithM.get(0),addedItems.get(0));

    }

    @Test(priority = 4)
    public void Task4Scenario2() {
        Ac = new AddItemToCartPage(driver);
        Ac.deleteItemsFromCart();
        Ac.changeCurrencytoEuro();

        List<Double> addedProducts = new ArrayList<>(List.of());
        //GO to Mackup Section And Add all sale items
        addedProducts.addAll(Ac.goToAllMakeupSubPagesAndAddSaleItems());
        //GO to Men Section And Add all sale items
        addedProducts.addAll(Ac.goToAllMenSubPagesAndAddSaleItems());
        Ac.gotoCart();

        Assert.assertEquals(Ac.totalSum(addedProducts),Ac.getTotalCartAmount());

        if(Ac.getTotalCartAmount() > 200){
            System.out.println("Amount is greater than 200€");
        }else {
            System.out.println("Amount is less than or equals to 200€");
        }

        Ac.changeCurrencytoDollar();
        if(Ac.getTotalCartAmount() > 200){
            System.out.println("Amount is greater than $200");

        }else {
            System.out.println("Amount is less than or equals to $200");
        }

        Ac.changeCurrencytoPound();
        if(Ac.getTotalCartAmount() > 200){
            System.out.println("Amount is greater than 200£");
        }else {
            System.out.println("Amount is less than or equals to 200£");
        }

        Ac.changeCurrencytoEuro();
        if(Ac.getItemsCountFromCart() < 15){
            System.out.println("Items are less than or equals to 15 in cart");
            //GO to Makeup Section And Add all sale items
            addedProducts.addAll(Ac.goToAllMakeupSubPagesAndAddNonSaleItemsUntil15());
            //GO to Men Section And Add all sale items
            addedProducts.addAll(Ac.goToAllMenSubPagesAndAddNonSaleItemsUntil15());
        }else {
            System.out.println("15 Items are in cart");
        }
        System.out.println(addedProducts);
        Assert.assertEquals(Ac.getItemsCountFromCart(),15);
        Assert.assertEquals(Ac.totalSum(addedProducts),Ac.getTotalCartAmount());
    }
}
