package Steps;

import infra.ui.DriverSetup;
import infra.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.api.ShoppingCart;
import logic.ui.*;
import logic.ui.Sound.Sound;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Steps {
    private TestContext context = new TestContext();
    Header header;

    @After
    public void tearDown() throws IOException {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.clearCartViaApi();
        DriverSetup driver = context.get("DriverSetup");
//        driver.close();
    }

    @Given("Im on the Rami Levy Home Page")
    public void im_on_the_rami_levy_home_page() {
        DriverSetup driver = new DriverSetup();
        context.put("DriverSetup", driver);
        driver.createPage(HomePage.class);
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
    public void i_enter_a_valid_email_and_password_and_click_login_button(String email, String password) throws InterruptedException {
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

    @When("I click to Drinks category")
    public void i_click_to_drinks_category() throws InterruptedException {
        DriverSetup driver = context.get("DriverSetup");
        driver.createPage(HomePage.class);
        HomePage homePage = driver.getCurrentPage();
        Thread.sleep(3000);
        homePage.clickOnDrinksCategory();
    }

    @And("I click to plus button on item and add it to the cart")
    public void i_click_to_plus_button_on_item_and_add_it_to_the_cart() {
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = driver.getCurrentPage();
        homePage.addItemToCart();

    }

    @Then("I should see the total sum in the cart is {string}")
    public void i_should_see_the_total_sum_in_the_cart_is(String string){
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = driver.getCurrentPage();;
        assertEquals(homePage.getCartSum(), string);
    }
    @Then("I should see the total amount of items is {string}")
    public void TotalAmountOfItmesInTheCart(String string){
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = driver.getCurrentPage();
        assertEquals(homePage.getCartNumOfItems(), string);
    }
    @And("I click on filter button and choose schweppes drinks")
    public void chooseSchweppesDrink(){
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = driver.getCurrentPage();
        homePage.chooseShweppesDrink();
    }
    @Then("verify i got a schweppes drink")
    public void verifySchweppesDrinksShowed(){
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = driver.getCurrentPage();
        assertEquals(homePage.verifySchweppesDrink(), "שוופס");
    }

    @When("I type milk in search input and click enter")
    public void insertToSearchInput(){
        DriverSetup driver = context.get("DriverSetup");
        driver.createPage(HomePage.class);
        HomePage homePage = driver.getCurrentPage();
        homePage.enterWordToSearch();
    }
    @Then("milk products must showed")
    public void VerifyResultofSearchInput(){
        DriverSetup driver = context.get("DriverSetup");
        HomePage homePage = driver.getCurrentPage();
        assertEquals(homePage.verifyItemViaComponents(), true);
    }

    @When("I Add Item With ID {string} And Quantity {string} To The Cart Via API")
    public void iAddItemWithIDAndQuantityToTheCartViaAPI(String itemId, String QTY) throws IOException {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItemToCartViaApi(itemId, QTY);
    }

    @Then("Validate If the Item Added To The Cart By Item Barcode {string} And Quantity {string}")
    public void validateIfTheItemAddedToTheCartByItemBarcode(String barcode,String QTY) {
        DriverSetup driver = context.get("DriverSetup");
        driver.createPage(HomePage.class);
        HomePage homePage = driver.getCurrentPage();
        Item item = homePage.getItemFromCartByBarcode(barcode);
        assertEquals(item.getBarcode(),barcode);
        assertEquals(item.getQuantity(),QTY);
    }

    @When("I search for Product By Name {string}")
    public void iSearchForProductByName(String itemName){
        DriverSetup driver = context.get("DriverSetup");
        driver.createPage(HomePage.class);
        HomePage homePage = driver.getCurrentPage();
        homePage.searchForItem(itemName);
    }

    @Then("The Product {string} Should Be The First Item At The Search Page")
    public void theProductShouldBeTheFirstItemAtTheSearchPage(String itemName) {
        DriverSetup driver = context.get("DriverSetup");
        driver.createPage(SearchPage.class);
        SearchPage searchPage = driver.getCurrentPage();
        assertEquals(searchPage.getPageTitle(),itemName);
        assertTrue(searchPage.checkIfItemFound(itemName));
    }

    @When("I Click On Search By Voice")
    public void iClickOnSearchByVoice() {
        DriverSetup driver = context.get("DriverSetup");
        driver.createPage(HomePage.class);
        HomePage homePage = driver.getCurrentPage();
        homePage.clickOnSearchVoice();
    }

    @And("I Play Audio File")
    public void iPlayAudioFile() {
        DriverSetup driver = context.get("DriverSetup");
        Sound sound = new Sound();
        sound.startVoice();
        driver.createPage(HomePage.class);
        HomePage homePage = driver.getCurrentPage();
        homePage.moveToSearchPage();
    }

    @And("I Should Be Redirected To The Search Page")
    public void iShouldBeRedirectidToTheSearchPage() {
        DriverSetup driver = context.get("DriverSetup");
        driver.createPage(SearchPage.class);
        SearchPage searchPage = driver.getCurrentPage();
        searchPage.closeDropDown();
    }
}
