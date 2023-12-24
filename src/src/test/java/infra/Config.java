package infra;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {
    @JsonProperty("chromeDriverPath")
    private String chromeDriverPath;

    @JsonProperty("uiURL")
    private String uiURL;


    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public String getUiURL() {
        return uiURL;
    }
}
