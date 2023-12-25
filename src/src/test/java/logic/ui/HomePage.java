package logic.ui;

import infra.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class HomePage extends BasePage {

    public static final By DRINKS_BTN =By.xpath("//*[@id='main-menu-8']");
    public static final By PLUS_BTN = By.xpath("//*[@id='Capa_1']");

    public static final By ITEM_DIV_TO_ADD = By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div/div/div[1]/div[2]/div[2]/div/div/div[1]/div/img");
    public static final By NUM_OF_ITEMS_IN_CART = By.xpath("//*[@id=\"onlineCartHeader\"]/div[1]/div[1]/span[1]");
    public static final By ESC_DELIVARY_BTN = By.xpath("//*[@id=\"close-popup\"]");
    public static final By CART_SUM = By.xpath("/html//div[@id='__layout']/div[@class='bg-gray-100 nuxt-wrap']//div[@role='complementary']/div[@class='position-relative wrap-online-cart']/div[3]/div[@role='button']//span[@role='hidden']");

    WebElement numOfItems;

    WebElement CartSum;
    WebElement drinksElementBtn;
    WebElement plusElementBtn;
    WebElement divElementToAdd;
    WebElement SpecificItem;
    WebElement escDelivaryBtn;
    public HomePage(WebDriver driver) {
        super(driver);
        initPage();
    }

    private void initPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.drinksElementBtn = wait.until(ExpectedConditions.presenceOfElementLocated(DRINKS_BTN));
        this.numOfItems = wait.until(ExpectedConditions.presenceOfElementLocated(NUM_OF_ITEMS_IN_CART));
    }
    public void clickOnDrinksCategory(){
        this.drinksElementBtn.click();
    }
    public void addItemToCart(){
        hoverOnDiv();
        clickAddButton();
    }
    private void hoverOnDiv() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.divElementToAdd = wait.until(ExpectedConditions.presenceOfElementLocated(ITEM_DIV_TO_ADD));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Actions actions = new Actions(driver);
        actions.moveToElement(this.divElementToAdd).perform();
    }
    private void clickAddButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.plusElementBtn = wait.until(ExpectedConditions.presenceOfElementLocated(PLUS_BTN));
        plusElementBtn.click();

    }



    public String getCartSum(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.CartSum = wait.until(ExpectedConditions.presenceOfElementLocated(CART_SUM));
        return  this.CartSum.getText();
    }
}
