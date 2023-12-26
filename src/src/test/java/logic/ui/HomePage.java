package logic.ui;

import infra.ui.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage extends BasePage {

    private static final By DRINKS_BTN =By.xpath("//*[@id='main-menu-8']");
    private static final By PLUS_BTN = By.xpath("//*[@id='Capa_1']");

    private static final By ITEM_DIV_TO_ADD = By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div/div/div[1]/div[2]/div[2]/div/div/div[1]/div/img");
    private static final By NUM_OF_ITEMS_IN_CART = By.xpath("//*[@id=\"onlineCartHeader\"]/div[1]/div[1]/span[1]");
    private static final By CART_SUM = By.xpath("/html//div[@id='__layout']/div[@class='bg-gray-100 nuxt-wrap']//div[@role='complementary']/div[@class='position-relative wrap-online-cart']/div[3]/div[@role='button']//span[@role='hidden']");
    private static final String CART_ITEM_BARCODE = "//div[@id='cart-product-";
    private static final String SEARCH_INPUT = "//*[@id='destination']";
    private static final String SEARCH_BY_VOICE = "//*[@id='search']/div/div/div[1]/div/div[1]/div/div[1]/div[1]/a";
    private static final String SEARCH_DROP_DOWN_BUTTON = "//*[@id='search']/div[2]/div/div[1]/div/div[2]/div/div/div[2]/div[2]/button[2]";

    WebElement numOfItems;
    WebElement CartSum;
    WebElement drinksElementBtn;
    WebElement plusElementBtn;
    WebElement divElementToAdd;
    WebElement itemInCart;
    WebElement searchInput;
    WebElement searchVoice;
    WebElement searchDropDownButton;

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

    public Item getItemFromCartByBarcode(String barcode) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        itemInCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CART_ITEM_BARCODE+barcode+"']")));

        if (itemInCart.isDisplayed()){
            String quantity = extractQuantity(itemInCart.getAttribute("aria-label"));
            String price = extractPrice(itemInCart.getAttribute("aria-label"));
            return new Item(itemInCart.getText(),price,quantity,barcode);
        }
        return null;
    }

    private String extractQuantity(String text) {
        String regex = "כמות\\s+(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
    private String extractPrice(String text) {
        String regex = "לתשלום\\s+(\\d+(\\.\\d+)?)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public void searchForItem(String itemName){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SEARCH_INPUT)));
        fillSearchInput(itemName);
        clickEnter(this.searchInput);
    }

    private void clickEnter(WebElement element) {
        element.sendKeys(Keys.ENTER);
    }

    private void fillSearchInput(String itemName) {
        this.searchInput.sendKeys(itemName);
    }
    public void clickOnSearchVoice(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.searchVoice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SEARCH_BY_VOICE)));
        this.searchVoice.click();
    }

    public void moveToSearchPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.searchDropDownButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SEARCH_DROP_DOWN_BUTTON)));
        this.searchDropDownButton.click();
    }
}
