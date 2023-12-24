package Steps;

import infra.ui.DriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import logic.ui.Header;
import logic.ui.HomePage;
import logic.ui.LoginPopup;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;

public class LoginSteps {
    DriverSetup driverSetup = new DriverSetup();
    WebDriver driver;
    Header header;
    @Before
    public void SetUp() {
        driver = driverSetup.setDriver();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Given(": Im on the Rami Levy Home Page")
    public void im_on_the_rami_levy_home_page() {
        HomePage homePage = new HomePage(driver);
    }

    @When(": I click on open popup")
    public void i_click_on_open_popup() {
        header = new Header(driver);
        header.openPopup();
    }

    @And(": I enter a valid {string} and {string} and click login button")
    public void i_enter_a_valid_email_and_password_and_click_login_button(String email, String password) {
        LoginPopup loginPopup = new LoginPopup(driver);
        loginPopup.Login(email, password);
    }

    @Then(": my {string} should appear in the header")
    public void my_name_should_appear_in_the_header(String name) {
        header = new Header(driver);
        assertEquals(header.getLoggedInName(),name);
    }
}
