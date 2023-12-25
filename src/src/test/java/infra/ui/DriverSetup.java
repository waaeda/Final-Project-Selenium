package infra.ui;

import infra.Config;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DriverSetup {
    ObjectMapper objectMapper = new ObjectMapper();
    Config config;
    private WebDriver driver;
    {
        try {
<<<<<<< HEAD
            config = objectMapper.readValue(new File("C:\\Users\\kamel\\IdeaProjects\\Qa Learning\\Final-Project-Selenium\\ConfigFile.json"), Config.class);
=======
            config = objectMapper.readValue(new File("/Users/waaedazzam/IdeaProjects/Final-Project-Selenium/ConfigFile.json"), Config.class);
>>>>>>> origin/main
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private BasePage currentPage;

    public DriverSetup() {
        System.setProperty("webdriver.chrome.driver", config.getChromeDriverPath());
        this.driver = new ChromeDriver();
        this.driver.get(config.getUiURL());
    }

    public WebDriver getDriver() {
        return this.driver;
    }
    public <T extends BasePage> T createPage(Class<T> pageType){
        return createPage(pageType, null);
    }
    public <T extends BasePage> T createPage(Class<T> pageType, String url){
        try {
            Constructor<T> constructor = pageType.getConstructor(WebDriver.class);
            if(url!=null){
                driver.get(url);
            }
            T page = constructor.newInstance(driver);
            currentPage = page;
            return page;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("relevant constructor not found", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public <T extends BasePage> T getCurrentPage(){
        return (T)currentPage;
    }
    public void close(){
        driver.close();
    }
}
