package testCases.automationteststore.StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.automationteststore.AddItemToCartPage;
import pageObjects.automationteststore.AddItemToCartPageCssSelector;

import java.util.ArrayList;
import java.util.List;
//import testCases.automationteststore.HooksClass;

public class LoginAndAddToCartStepDefinitions {
    BaseStep baseStep;

    AddItemToCartPage Ac;
    AddItemToCartPageCssSelector Acs;
    List<String> addedItems;

    @Given("I am logged in to the website")
    public void iAmLoggedInToTheWebsite() {
        baseStep = new BaseStep();
        new AddItemToCartPage(baseStep.driver).changeCurrencytoDollar();
    }


    @And("If any item in cart then I Delete Items From Cart")
    public void ifAnyItemInCartThenIDeleteItemsFromCart() {
        Ac = new AddItemToCartPage(baseStep.driver);
        Ac.deleteItemsFromCart();
    }

    @And("Go to the home page")
    public void goToTheHomePage() {
        baseStep.GotoHomePage();
    }

    @When("I scroll the page and click the brand:DOVE")
    public void i_scroll_the_page_and_click_the_brand_dove() {
        Ac.selectBrand("//ul[@id=\"brandcarousal\"]/li[7]");
    }
    @When("I will sort the items by Date Old > New")
    public void i_will_sort_the_items_by_date_old_new() {
        Ac.sortItems("Date Old > New");
    }
    @When("I add the select newest item and to the cart")
    public void i_add_the_select_newest_item_and_to_the_cart() {
        Ac.addNewestItem();
    }
    @Then("I verify that the Dove item is added to the cart")
    public void i_verify_that_the_dove_item_is_added_to_the_cart() {
        Assert.assertEquals(Ac.getCartItemCount(),1);
        Assert.assertEquals(Ac.getCartQuantity(),1);
        Assert.assertEquals(Ac.getTotalCartAmount(),7.2);
    }


//    Scenario 2 : (do it by css selectors)
    @When("I go to the APPAREL & ACCESSORIES section")
    public void i_go_to_the_apparel_accessories_section() {
        Acs = new AddItemToCartPageCssSelector(baseStep.driver);
        Acs.gotoApparelAndAccessories();
    }

    @When("I go to Tshirts")
    public void i_go_to_tshirts() {
        Acs.gotoTshirts();
    }
    @When("I sort the Tshirts by Price low to high")
    public void i_sort_the_tshirts_by_low_to_high_rate() {
        Acs.sortItems("Price Low > High");
    }
    @When("I select the top three lowest value Tshirts")
    public void i_select_the_top_lowest_value_tshirts() {
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
    }

    @When("I go back to the APPAREL & ACCESSORIES section")
    public void i_go_back_to_the_apparel_accessories_section() {
        // Write code here that turns the phrase above into concrete actions
        Acs.gotoApparelAndAccessories();
    }
    @When("I go to the shoes section")
    public void i_go_to_the_shoes_section() {
        Acs.goToShoes();
    }


    @And("I sort the Tshirts by Price high to low")
    public void iSortTheTshirtsByPriceHighToLow() {
        Acs.sortItems("Price High > Low");
    }

    @When("I add the highest value shoe to the cart with quantity two")
    public void i_add_the_highest_value_shoe_to_the_cart_with_quantity() {
        Acs.selectItem("div:nth-child(1) > div.thumbnail > a > img");
        Acs.setProductQuantity("2");
        Acs.addItem();
    }

    @Then("I verify that selected items should be in the cart")
    public void iVerifyThatSelectedItemsShouldBeInTheCart() {
        Assert.assertEquals(Acs.getCartItemCount(),3);
        Assert.assertEquals(Acs.getCartQuantity(),4);
        Assert.assertEquals(Acs.getTotalCartAmount(),14+32+(110*2));
        Assert.assertEquals(Acs.getItemsInCart().get(0),"Casual 3/4 Sleeve Baseball T-Shirt");
        Assert.assertEquals(Acs.getItemsInCart().get(1), "Designer Men Casual Formal Double Cuffs Grandad Band Collar Shirt Elegant Tie");
        Assert.assertEquals(Acs.getItemsInCart().get(2), "Fiorella Purple Peep Toes");
    }

//    Scenario 4: (do it by xpath selectors)

    @When("I go to the Men section")
    public void i_go_to_the_men_section() {
        Ac.gotoMen();
    }
    @When("I add the product ending with M to the cart")
    public void i_add_the_product_ending_with_m_to_the_cart() {
        addedItems = Ac.addItemsEndWithM();
    }
    @Then("verify that the item in the cart should end with M")
    public void verify_that_the_item_in_the_cart_should_end_with_m() {
        List<String> itemsInCartwithM = Ac.getItemsFromCartPageEndWithM();
        Assert.assertEquals(itemsInCartwithM.get(0),addedItems.get(0));

    }


//    Advance task 4 Scenario: (do it by xpath selectors)
    List<Double> addedProducts = new ArrayList<>(List.of());
    @When("I change Currency to Euro")
    public void i_change_currency_to_euro() {
        Ac.changeCurrencytoEuro();
    }
    @When("Go to the All Makeup Sub Pages And Add Sale Items")
    public void go_to_the_all_makeup_sub_pages_and_add_sale_items() {
        //GO to Mackup Section And Add all sale items
        addedProducts.addAll(Ac.goToAllMakeupSubPagesAndAddSaleItems());

    }
    @When("Go to the All Men Sub Pages And Add Sale Items")
    public void go_to_the_all_men_sub_pages_and_add_sale_items() {
        addedProducts.addAll(Ac.goToAllMenSubPagesAndAddSaleItems());
    }
    @When("I go to the cart page")
    public void i_go_to_the_cart_page() {
        Ac.gotoCart();
    }
    @When("Check if amount > than 200€ delete 1 item then check again repeat till price <=200")
    public void check_if_amount_than_€_delete_item_then_check_again_repeat_till_price() {
        if(Ac.getTotalCartAmount() > 200){
            System.out.println("Amount is greater than 200€");
        }else {
            System.out.println("Amount is less than or equals to 200€");
        }
    }
    @When("Change currency to dollar")
    public void change_currency_to_dollar() {
        // Write code here that turns the phrase above into concrete actions
        Ac.changeCurrencytoDollar();
    }
    @When("Check if amount > than $200 delete 1 item then check again repeat till price <=200")
    public void check_if_amount_than_$_delete_item_then_check_again_repeat_till_price() {
        if(Ac.getTotalCartAmount() > 200){
            System.out.println("Amount is greater than $200");

        }else {
            System.out.println("Amount is less than or equals to $200");
        }
    }
    @When("Change currency to pound")
    public void change_currency_to_pound() {
        Ac.changeCurrencytoPound();
    }
    @When("Check if amount > than £200 delete 1 item then check again repeat till price <=200")
    public void check_if_amount_than_£_delete_item_then_check_again_repeat_till_price() {
        if(Ac.getTotalCartAmount() > 200){
            System.out.println("Amount is greater than 200£");
        }else {
            System.out.println("Amount is less than or equals to 200£");
        }
    }

    @When("check if item count is less than 15 then add All NonSale items under sections of Men & Makeup")
    public void check_if_item_count_is_less_than_then_add_all_non_sale_items_under_sections_of_men_makeup() {
        Ac.changeCurrencytoEuro();
        if(Ac.getItemsCountFromCart() < 15){
            System.out.println("Items are less than or equals to 15 in cart");
            addedProducts.addAll(Ac.goToAllMakeupSubPagesAndAddNonSaleItemsUntil15());
            addedProducts.addAll(Ac.goToAllMenSubPagesAndAddNonSaleItemsUntil15());
        }else {
            System.out.println("15 Items are in cart");
        }
    }
    @Then("verify that the item in the cart should be 15 and total amount should be equal to previous total + new items amount")
    public void verify_that_the_item_in_the_cart_should_be() {
        Assert.assertEquals(Ac.getItemsCountFromCart(),15);
        Assert.assertEquals(Ac.totalSum(addedProducts),Ac.getTotalCartAmount());
    }


}