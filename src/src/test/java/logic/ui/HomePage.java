package logic.ui;

import infra.ui.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class HomePage extends BasePage {

    private static final By DRINKS_BTN =By.xpath("//*[@id='main-menu-8']");
    private static final By PLUS_BTN = By.xpath("//*[@id='Capa_1']");

    private static final By ITEM_DIV_TO_ADD = By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div/div/div[1]/div[2]/div[2]/div/div/div[1]/div/img");
    private static final By NUM_OF_ITEMS_IN_CART = By.xpath("//*[@id=\"onlineCartHeader\"]/div[1]/div[1]/span[1]");
    private static final By CART_SUM = By.xpath("/html//div[@id='__layout']/div[@class='bg-gray-100 nuxt-wrap']//div[@role='complementary']/div[@class='position-relative wrap-online-cart']/div[3]/div[@role='button']//span[@role='hidden']");

    private static final By SALE_BUTTON = By.xpath(("//li[@id='sale']/a[@role='button']"));
    private static final By SALE_PRODUCTS = By.xpath("/html//span[@id='show-sales']");
    private static final By PRODUCT_ON_SALE = By.xpath("/html//div[@id='main-content']/div[2]/div/div[1]/button//div[@class='position-realtive']");
    private static final By DISCOUNTED_PRICE = By.xpath("/html/body/div[5]/div[@role='dialog']//div[@class='modal-content']/div/div[@class='wrap-popup-sale-modal']/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div/div[3]/div[@class='line-height-1']/div[3]/span/span[@role='hidden']");

    WebElement numOfItems;

    WebElement CartSum;
    WebElement drinksElementBtn;
    WebElement plusElementBtn;
    WebElement divElementToAdd;
    WebElement sale_button;
    WebElement Sale_products;

    WebElement product_on_sale;
    WebElement discounted_price;
    WebElement origin_price;

    public HomePage(WebDriver driver) {
        super(driver);
        initPage();
    }

    private void initPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        this.numOfItems = wait.until(ExpectedConditions.presenceOfElementLocated(NUM_OF_ITEMS_IN_CART));
    }
    public void clickOnDrinksCategory(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.drinksElementBtn = wait.until(ExpectedConditions.presenceOfElementLocated(DRINKS_BTN));
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

    private void clickSaleButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.sale_button = wait.until(ExpectedConditions.presenceOfElementLocated(SALE_BUTTON));
        sale_button.click();
    }
    private void allSaleProducts() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.Sale_products = wait.until(ExpectedConditions.presenceOfElementLocated(SALE_PRODUCTS));
        Sale_products.click();
    }

    public void onlySaleProducts(){
        clickSaleButton();
        allSaleProducts();
    }

    public void productOnSaleClick(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.product_on_sale = wait.until(ExpectedConditions.presenceOfElementLocated(PRODUCT_ON_SALE));
        product_on_sale.click();
    }

    public String getDiscountedPrice(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.discounted_price = wait.until(ExpectedConditions.presenceOfElementLocated(DISCOUNTED_PRICE));
        return this.discounted_price.getText();
    }



}
