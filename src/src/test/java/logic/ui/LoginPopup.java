package logic.ui;

import infra.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPopup extends BasePage {
    private static final String EMAIL_INPUT = "email";
    private static final String PASSWORD_INPUT = "password";
    private static final String SUBMIT_BUTTON = "//button[@aria-label='כניסה']";

    WebElement email;
    WebElement password;
    WebElement submit;
    public LoginPopup(WebDriver driver) {
        super(driver);
        initPopup();
    }

    private void initPopup() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        this.email = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(EMAIL_INPUT)));
        this.password = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(PASSWORD_INPUT)));
        this.submit = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SUBMIT_BUTTON)));
    }
    public void Login(String email,String password){
        fillEmail(email);
        fillPassword(password);
        clickSubmit();
    }

    private void clickSubmit() {
        this.submit.click();
    }

    private void fillPassword(String password) {
        this.password.sendKeys(password);
    }

    private void fillEmail(String email) {
        this.email.sendKeys(email);
    }

}
