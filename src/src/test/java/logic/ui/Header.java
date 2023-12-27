package logic.ui;

import infra.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header extends BasePage {
    private final String POPUP_BUTTON = "//*[@id='login-user']";
    private final String USER_LOGGIDIN_NAME = "//*[@id='login-user']/div/div/div[2]/div/span";

    private WebElement popupButton;
    private WebElement userLoggidinName;

    public Header(WebDriver driver) {
        super(driver);
        initHeader();
    }

    private void initHeader() {
        this.popupButton = this.driver.findElement(By.xpath(POPUP_BUTTON));
    }

    public void openPopup() {
        this.popupButton.click();
    }

    public String getLoggedInName() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.userLoggidinName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(USER_LOGGIDIN_NAME)));
        return this.userLoggidinName.getText();
    }


}
