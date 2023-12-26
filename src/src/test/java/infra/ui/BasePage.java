package infra.ui;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().window().maximize();
    }
}
