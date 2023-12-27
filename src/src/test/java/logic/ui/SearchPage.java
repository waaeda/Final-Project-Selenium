package logic.ui;

import infra.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {
    private final String PAGE_TITLE = "//*[@id='result-search']/div/span[2]";
    private final String CLOSE_DROP_DOWN = "//*[@id='search']/div[2]/div/div[1]/div/div[1]/div/div[1]/div[1]/div/a";

    private WebElement pageTitle;
    private WebElement firstItemName;
    private WebElement closeDropDown;

    public SearchPage(WebDriver driver) {
        super(driver);
        initPage();
    }

    WebDriverWait wait;

    private void initPage() {
        wait = new WebDriverWait(this.driver, 20);
        pageTitle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PAGE_TITLE)));
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public boolean checkIfItemFound(String itemName) {
        firstItemName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + itemName + "']")));
        if (firstItemName != null) {
            return true;
        }
        return false;
    }

    public void closeDropDown() {
        closeDropDown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLOSE_DROP_DOWN)));
        this.closeDropDown.click();
    }
}
