package infra.api;

import infra.Config;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpFacade {
    static ObjectMapper objectMapper = new ObjectMapper();
    static Config config;

    static {
        try {
            config = objectMapper.readValue(new File("/Users/waaedazzam/IdeaProjects/Final-Project-Selenium/ConfigFile.json"), Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
