package logic.ui;

import infra.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Header extends BasePage {
    private static final String POPUP_BUTTON = "//*[@id='login-user']";
    private static final String USER_LOGGIDIN_NAME = "//*[@id='login-user']/div/div/div[2]/div/span";
    private static final String LOG_OUT_BUTTON = "/html//div[@id='__layout']/div[@class='bg-gray-100 nuxt-wrap']/div[1]/div[1]/div[3]/div[3]/div/div[2]/div[4]/div//div[@role='list']/div[7]/div[@role='link']";
    WebElement popupButton;
    WebElement userLoggidinName;
    WebElement addToCartButton;
    WebElement logOutButton;
    public Header(WebDriver driver) {
        super(driver);
        initHeader();
    }

    private void initHeader() {
        this.popupButton = this.driver.findElement(By.xpath(POPUP_BUTTON));
        this.userLoggidinName = this.driver.findElement(By.xpath(USER_LOGGIDIN_NAME));
        this.logOutButton = this.driver.findElement(By.xpath(LOG_OUT_BUTTON));
    }
    public void openPopup(){
        this.popupButton.click();
    }
    public String getLoggedInName(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.userLoggidinName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(USER_LOGGIDIN_NAME)));
        return  this.userLoggidinName.getText();
    }

    public void clickOnLoggedInNam(){
        this.userLoggidinName.click();

    }

    public void clickLogOut(){
        this.logOutButton.click();
    }
}
