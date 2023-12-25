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

    @Given("the user has a shopping cart")
    public void the_user_has_a_shopping_cart(){
        DriverSetup driver = new DriverSetup();
        context.put("DriverSetup", driver);
        HomePage homePage = driver.createPage(HomePage.class);
        shoppingCart = new ShoppingCart();
    }

    @When("the user adds an item with ID {string} and quantity {string} to the cart")
    public void theUserAddsAnItemWithIDAndQuantityToTheCart(String itemId, String QTY) throws IOException {
        shoppingCart.addItemtToCartViaApi(itemId,QTY);
    }
}
