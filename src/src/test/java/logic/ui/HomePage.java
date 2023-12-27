package logic.ui;

import infra.ui.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomePage extends BasePage {

    private  final By DRINKS_BTN = By.xpath("//*[@id='main-menu-8']");
    private  final By PLUS_BTN = By.xpath("//*[@id='Capa_1']");

    private  final By ITEM_DIV_TO_ADD = By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div/div/div[1]/div[2]/div[5]/div/div/div[1]/div/img");

    private  final By NUM_OF_ITEMS_IN_CART = By.xpath("//*[@id='onlineCartHeader']/div[1]/div[1]/span[1]");

    private  final By ESC_DELEVirY = By.xpath("//*[@id='close-popup']");
    private  final By FILTER_BTN = By.xpath("//*[@id=\"search\"]/div/div/div[1]/div/div[1]/div/div[1]/div[3]");
    private  final By SCHWEPPES_FILTER_BTN = By.xpath(" //*[@id=\"__layout\"]/div/div[1]/div[1]/div[3]/div[3]/div/div[1]/div[2]/div/div[3]/div[3]");

    private  final By SCHWEPPES_DIV_ITEM = By.cssSelector("img[src*='/product/7290019056119/small.jpg']");
    private  final By SEARCH_INPUT1 = By.xpath("//*[@id=\"destination\"]");
    private  final By SEARCH_RESULT_PRODUCT_DIV = By.xpath("//*[@id=\"min-height-product-1\"]/div/div/div[2]");
    private  final By DIV_INFORMATION_PRODUCT = By.cssSelector("div[data-v-6039ce16] > div[data-v-6039ce16] > div[data-v-6039ce16]");
    private  final String CART_ITEM_BARCODE = "//div[@id='cart-product-";
    private  final String SEARCH_INPUT = "//*[@id='destination']";
    private  final String SEARCH_BY_VOICE = "//*[@id='search']/div/div/div[1]/div/div[1]/div/div[1]/div[1]/a";
    private  final String SEARCH_DROP_DOWN_BUTTON = "//*[@id='search']/div[2]/div/div[1]/div/div[2]/div/div/div[2]/div[2]/button[2]";
    private  final By CART_SUM = By.xpath("/html//div[@id='__layout']/div[@class='bg-gray-100 nuxt-wrap']//div[@role='complementary']/div[@class='position-relative wrap-online-cart']/div[3]/div[@role='button']//span[@role='hidden']");
    private  final By SALE_BUTTON = By.xpath(("//li[@id='sale']/a[@role='button']"));
    private  final By SALE_PRODUCTS = By.xpath("/html//span[@id='show-sales']");
    private  final By PRODUCT_ON_SALE = By.xpath("/html//div[@id='main-content']/div[2]/div/div[1]/button//div[@class='position-realtive']");
    private  final By DISCOUNTED_PRICE = By.xpath("/html/body/div[3]/div[@role='dialog']//div[@class='modal-content']/div/div[@class='wrap-popup-sale-modal']/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div/div[3]/div[@class='line-height-1']/div[3]/span/span[@role='hidden']");


    private WebElement divInformationProduct;
    private WebElement searchInput;
    private WebElement searchResultProductDiv;
    private WebElement schweppesFilterbtn;
    private WebElement schweppesDivItemElement;
    private WebElement filterBtnElement;
    private WebElement numOfItems;
    private WebElement CartSum;
    private WebElement drinksElementBtn;
    private WebElement plusElementBtn;
    private WebElement divElementToAdd;
    private WebElement itemInCart;
    private WebElement searchVoice;
    private WebElement searchDropDownButton;
    private WebElement sale_button;
    private WebElement Sale_products;

    private WebElement product_on_sale;
    private WebElement discounted_price;

    private WebDriverWait wait;
    public HomePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 20);
    }

    public void clickOnDrinksCategory() {
        this.drinksElementBtn = wait.until(ExpectedConditions.presenceOfElementLocated(DRINKS_BTN));
        this.drinksElementBtn.click();
    }

    public void addItemToCart() {
        hoverOnDiv();
        clickAddButton();
    }

    private void hoverOnDiv() {
        this.divElementToAdd = wait.until(ExpectedConditions.presenceOfElementLocated(ITEM_DIV_TO_ADD));
        Actions actions = new Actions(driver);
        actions.moveToElement(this.divElementToAdd).perform();
    }

    private void clickAddButton() {
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

    public String getCartNumOfItems() {
        this.numOfItems = wait.until(ExpectedConditions.presenceOfElementLocated(NUM_OF_ITEMS_IN_CART));
        return numOfItems.getText();
    }

    public String getCartSum() {
        this.CartSum = wait.until(ExpectedConditions.presenceOfElementLocated(CART_SUM));
        return this.CartSum.getText();
    }


    private void clickSaleButton() {
        this.sale_button = wait.until(ExpectedConditions.presenceOfElementLocated(SALE_BUTTON));
        sale_button.click();
    }

    private void allSaleProducts() {
        this.Sale_products = wait.until(ExpectedConditions.presenceOfElementLocated(SALE_PRODUCTS));
        Sale_products.click();
    }

    public void onlySaleProducts() {
        clickSaleButton();
        allSaleProducts();
    }

    public void productOnSaleClick() {
        this.product_on_sale = wait.until(ExpectedConditions.presenceOfElementLocated(PRODUCT_ON_SALE));
        product_on_sale.click();
    }

    public String getDiscountedPrice() {
        this.discounted_price = wait.until(ExpectedConditions.presenceOfElementLocated(DISCOUNTED_PRICE));
        return this.discounted_price.getText();
    }

    private void clickFilterBtn() {
        this.filterBtnElement = wait.until(ExpectedConditions.presenceOfElementLocated(FILTER_BTN));
        filterBtnElement.click();
    }

    private void clickOnSchweppesBtn() {
        this.schweppesFilterbtn = drinksElementBtn.findElement(SCHWEPPES_FILTER_BTN);
        schweppesFilterbtn.click();
    }

    public void chooseShweppesDrink() {
        clickFilterBtn();
        clickOnSchweppesBtn();
    }

    public String verifySchweppesDrink() {
        this.schweppesDivItemElement = wait.until(ExpectedConditions.presenceOfElementLocated(SCHWEPPES_DIV_ITEM));
        String fullNameDrinkType = schweppesDivItemElement.getAttribute("alt");
        String extractSchweppes = fullNameDrinkType.split("\\s+")[0];
        return extractSchweppes;
    }


    public void enterWordToSearch() {
        searchInput = driver.findElement(SEARCH_INPUT1);
        searchInput.sendKeys("חלב");
        searchInput.sendKeys(Keys.RETURN);
    }

    public Item getItemFromCartByBarcode(String barcode) {
        itemInCart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CART_ITEM_BARCODE + barcode + "']")));

        if (itemInCart.isDisplayed()) {
            String quantity = extractQuantity(itemInCart.getAttribute("aria-label"));
            String price = extractPrice(itemInCart.getAttribute("aria-label"));
            return new Item(itemInCart.getText(), price, quantity, barcode);
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

    public void searchForItem(String itemName) {
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

    public void clickOnSearchVoice() {
        this.searchVoice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SEARCH_BY_VOICE)));
        this.searchVoice.click();
    }

    public void moveToSearchPage() {
        this.searchDropDownButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SEARCH_DROP_DOWN_BUTTON)));
        this.searchDropDownButton.click();
    }

    public boolean verifyItemViaComponents() {
        clickOnResultProduct();
        return checkCompnentsOfTheProduct();
    }

    private boolean checkCompnentsOfTheProduct() {
        this.divInformationProduct = wait.until(ExpectedConditions.presenceOfElementLocated(DIV_INFORMATION_PRODUCT));
        String component = divInformationProduct.getText();
        if (component.contains("חלב")) {
            return true;
        }
        return false;
    }

    private void clickOnResultProduct() {
        this.searchResultProductDiv = wait.until(ExpectedConditions.presenceOfElementLocated(SEARCH_RESULT_PRODUCT_DIV));
        searchResultProductDiv.click();
    }

}

