package Steps;

import infra.ui.DriverSetup;
import infra.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.api.ShoppingCart;
import logic.ui.Header;
import logic.ui.HomePage;
import logic.ui.LoginPopup;

import java.io.IOException;

import static java.lang.Float.parseFloat;
import static org.testng.Assert.assertEquals;

public class Steps {
    private TestContext context = new TestContext();
    Header header;

    @After
    public void tearDown() throws IOException {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.clearCartViaApi();
        DriverSetup driver = context.get("DriverSetup");
        driver.close();
    }

    @Given("Im on the Rami Levy Home Page")
    public void im_on_the_rami_levy_home_page() {
        DriverSetup driver = new DriverSetup();
        context.put("DriverSetup", driver);
    }

    @When("I click on open popup")
    public void i_click_on_open_popup() {
        DriverSetup driver = context.get("DriverSetup");
        header = driver.createPage(Header.class);
        header = driver.getCurrentPage();
        header.openPopup();
        driver.createPage(LoginPopup.class);
    }

    @And("I enter a valid {string} and {string} and click login button")
    public void i_enter_a_valid_email_and_password_and_click_login_button(String email, String password) {
        DriverSetup driver = context.get("DriverSetup");
        LoginPopup loginPopup = driver.getCurrentPage();
        loginPopup.Login(email, password);
        driver.createPage(Header.class);
    }

    @Then("my {string} should appear in the header")
    public void my_name_should_appear_in_the_header(String name) {
        DriverSetup driver = context.get("DriverSetup");
        header = driver.getCurrentPage();
        assertEquals(header.getLoggedInName(), name);
    }

    @When("I navigate to the Drinks category")
    public void i_navigate_to_the_category() {
        // Write code here that turns the phrase above into concrete
        DriverSetup driver = context.get("DriverSetup");
        driver.createPage(HomePage.class);
        HomePage homePage = driver.getCurrentPage();
        homePage.clickOnDrinksCategory();
    }

    @And("add item to the cart by clicking the plus button")
    public void add_item_to_the_cart_by_clicking_the_plus_button() {
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = driver.getCurrentPage();
        homePage.addItemToCart();

    }

    @Then("I should observe that the total sum in the cart is displayed as {string}")
    public void i_should_observe_that_the_total_sum_in_the_cart_is_displayed_as(String string) {
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = driver.getCurrentPage();
        assertEquals(homePage.getCartSum(), string);
    }

    @When("I Add Item With ID {string} And Quantity {string} To The Cart Via API")
    public void iAddItemWithIDAndQuantityToTheCartViaAPI(String itemId, String QTY) throws IOException {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItemToCartViaApi(itemId, QTY);
    }

    @When("I click on the Sale button in the navbar and then sale only button")
    public void i_click_on_the_sale_button_in_the_navbar_and_then_sale_only_button() {
        DriverSetup driver = context.get("DriverSetup");
        driver.createPage(HomePage.class);
        HomePage homePage = driver.getCurrentPage();
        homePage.onlySaleProducts();



    }
    @When("I select a product under the sale section")
    public void i_select_a_product_under_the_sale_section() {
        DriverSetup driver = context.get("DriverSetup");
        driver.createPage(HomePage.class);
        HomePage homePage = driver.getCurrentPage();
        homePage.productOnSaleClick();
    }
    @Then("I validate that the new price reflects a {int}% discount and the Origin price is {float}")
    public void i_validate_that_the_new_price_reflects_a_discount_and_the_Origin_price_is(Integer precentage, Float origin_price) {
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = driver.getCurrentPage();
        float discount =origin_price *(1-((float) precentage / 100));
        String discountedPrice= String.valueOf(discount);
        assertEquals(homePage.getDiscountedPrice() , discountedPrice+" ₪");
    }
}
