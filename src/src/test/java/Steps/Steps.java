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

import static org.testng.Assert.assertEquals;

public class Steps {
    private TestContext context = new TestContext();
    Header header;
    ShoppingCart shoppingCart;
    @After
    public void tearDown(){
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
        assertEquals(header.getLoggedInName(),name);
    }

    @When("I click to Drinks category")
    public void i_click_to_drinks_category() {
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = new HomePage(driver.getDriver());
        homePage.clickOnDrinksCategory();
    }


    @And("I click to plus button on item and add it to the cart")
    public void i_click_to_plus_button_on_item_and_add_it_to_the_cart() {
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = new HomePage(driver.getDriver());
        homePage.addItemToCart();

    }

    @Then("I should see the total sum in the cart is {string}")
    public void i_should_see_the_total_sum_in_the_cart_is(String string) {
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = new HomePage(driver.getDriver());
        assertEquals(homePage.getCartSum(), string);

    }






}
