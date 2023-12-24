package infra.ui;

import infra.Config;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class DriverSetup {
    ObjectMapper objectMapper = new ObjectMapper();
    Config config;

    {
        try {
            config = objectMapper.readValue(new File("/Users/waaedazzam/IdeaProjects/Final-Project-Selenium/ConfigFile.json"), Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WebDriver setDriver() {
        System.setProperty("webdriver.chrome.driver", config.getChromeDriverPath());
        ChromeDriver driver = new ChromeDriver();
        driver.get(config.getUiURL());
        return driver;
    }
}
