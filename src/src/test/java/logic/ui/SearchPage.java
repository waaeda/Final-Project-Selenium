package logic.ui;

import infra.ui.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {
    private static final String PAGE_TITLE = "//*[@id='result-search']/div/span[2]";
    private static final String CLOSE_DROP_DOWN = "//*[@id='search']/div[2]/div/div[1]/div/div[1]/div/div[1]/div[1]/div/a";

    WebElement pageTitle;
    WebElement firstItemName;
    WebElement closeDropDown;
    public SearchPage(WebDriver driver) {
        super(driver);
        initPage();
    }

    private void initPage() {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        pageTitle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PAGE_TITLE)));
    }

    public String getPageTitle() {
        return  pageTitle.getText();
    }

    public boolean checkIfItemFound(String itemName) {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        firstItemName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + itemName + "']")));
        if (firstItemName != null){
            return true;
        }
        return false;
    }

    public void closeDropDown(){
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        closeDropDown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CLOSE_DROP_DOWN)));
        this.closeDropDown.click();
    }
}
