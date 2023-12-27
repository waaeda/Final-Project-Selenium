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

    private static final By ITEM_DIV_TO_ADD = By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div/div/div[1]/div[2]/div[5]/div/div/div[1]/div/img");

    private static final By NUM_OF_ITEMS_IN_CART = By.xpath("//*[@id='onlineCartHeader']/div[1]/div[1]/span[1]");

    private static final By ESC_DELEVirY = By.xpath("//*[@id='close-popup']");
    private static final By FILTER_BTN = By.xpath("//*[@id=\"search\"]/div/div/div[1]/div/div[1]/div/div[1]/div[3]");
    private static final By SCHWEPPES_FILTER_BTN = By.xpath(" //*[@id=\"__layout\"]/div/div[1]/div[1]/div[3]/div[3]/div/div[1]/div[2]/div/div[3]/div[3]");

    private static final By SCHWEPPES_DIV_ITEM = By.cssSelector("img[src*='/product/7290019056119/small.jpg']");
    private static final By SEARCH_INPUT1 = By.xpath("//*[@id=\"destination\"]");
    private static final By SEARCH_RESULT_PRODUCT_DIV = By.xpath("//*[@id=\"min-height-product-1\"]/div/div/div[2]");
    private static final By DIV_INFORMATION_PRODUCT =By.cssSelector("div[data-v-6039ce16] > div[data-v-6039ce16] > div[data-v-6039ce16]");
    private static final String CART_ITEM_BARCODE = "//div[@id='cart-product-";
    private static final String SEARCH_INPUT = "//*[@id='destination']";
    private static final String SEARCH_BY_VOICE = "//*[@id='search']/div/div/div[1]/div/div[1]/div/div[1]/div[1]/a";
    private static final String SEARCH_DROP_DOWN_BUTTON = "//*[@id='search']/div[2]/div/div[1]/div/div[2]/div/div/div[2]/div[2]/button[2]";
    private static final By CART_SUM = By.xpath("/html//div[@id='__layout']/div[@class='bg-gray-100 nuxt-wrap']//div[@role='complementary']/div[@class='position-relative wrap-online-cart']/div[3]/div[@role='button']//span[@role='hidden']");
    private static final By SALE_BUTTON = By.xpath(("//li[@id='sale']/a[@role='button']"));
    private static final By SALE_PRODUCTS = By.xpath("/html//span[@id='show-sales']");
    private static final By PRODUCT_ON_SALE = By.xpath("/html//div[@id='main-content']/div[2]/div/div[1]/button//div[@class='position-realtive']");
    private static final By DISCOUNTED_PRICE = By.xpath("/html/body/div[3]/div[@role='dialog']//div[@class='modal-content']/div/div[@class='wrap-popup-sale-modal']/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div/div[3]/div[@class='line-height-1']/div[3]/span/span[@role='hidden']");



    WebElement divInformationProduct;
    WebElement searchInput;
    WebElement searchResultProductDiv;

    WebElement schweppesFilterbtn;
    WebElement schweppesDivItemElement;

    WebElement filterBtnElement;
    WebElement numOfItems;
    WebElement CartSum;

    WebElement drinksElementBtn;
    WebElement plusElementBtn;
    WebElement divElementToAdd;
    WebElement itemInCart;

    WebElement searchVoice;
    WebElement searchDropDownButton;
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
        Actions actions = new Actions(driver);
        actions.moveToElement(this.divElementToAdd).perform();
    }
    private void clickAddButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.plusElementBtn = wait.until(ExpectedConditions.presenceOfElementLocated(PLUS_BTN));
        plusElementBtn.click();
        try {
            WebElement escElement = wait.until(ExpectedConditions.presenceOfElementLocated(ESC_DELEVirY));

            if (escElement != null) {
                escElement.click();
            }
        } catch (Exception e) {

        }
    }

    public String getCartNumOfItems(){
        numOfItems = driver.findElement(NUM_OF_ITEMS_IN_CART);
        return numOfItems.getText();
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
    private void clickFilterBtn(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.filterBtnElement = wait.until(ExpectedConditions.presenceOfElementLocated(FILTER_BTN));
        filterBtnElement.click();
    }
    private void clickOnSchweppesBtn(){
        this.schweppesFilterbtn = drinksElementBtn.findElement(SCHWEPPES_FILTER_BTN);
        schweppesFilterbtn.click();
    }
    public void chooseShweppesDrink(){
        clickFilterBtn();
        clickOnSchweppesBtn();
    }
    public String verifySchweppesDrink(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.schweppesDivItemElement = wait.until(ExpectedConditions.presenceOfElementLocated(SCHWEPPES_DIV_ITEM));
        String fullNameDrinkType = schweppesDivItemElement.getAttribute("alt");
        String extractSchweppes = fullNameDrinkType.split("\\s+")[0];
        return extractSchweppes;
    }



    public void enterWordToSearch(){
        searchInput = driver.findElement(SEARCH_INPUT1);
        searchInput.sendKeys("חלב");
        searchInput.sendKeys(Keys.RETURN);
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

    public boolean verifyItemViaComponents(){
        clickOnResultProduct();
        return checkCompnentsOfTheProduct();
    }

    private boolean checkCompnentsOfTheProduct() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.divInformationProduct =wait.until(ExpectedConditions.presenceOfElementLocated(DIV_INFORMATION_PRODUCT));
        String component = divInformationProduct.getText();
        if(component.contains("חלב")){
            return true;
        }
        return false;
    }

    private void clickOnResultProduct() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        this.searchResultProductDiv = wait.until(ExpectedConditions.presenceOfElementLocated(SEARCH_RESULT_PRODUCT_DIV));
        searchResultProductDiv.click();
    }

}

