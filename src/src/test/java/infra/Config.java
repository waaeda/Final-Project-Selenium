package infra;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {
    @JsonProperty("chromeDriverPath")
    private String chromeDriverPath;
    @JsonProperty("uiURL")
    private String uiURL;
    @JsonProperty("apiURL")
    private String apiURL;
    @JsonProperty("ecomToken")
    private String ecomToken;
    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public String getUiURL() {
        return uiURL;
    }

    public String getApiURL() {
        return apiURL;
    }

    public String getEcomToken() {
        return ecomToken;
    }
}
