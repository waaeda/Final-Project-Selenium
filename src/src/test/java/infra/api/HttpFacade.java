package infra.api;

import infra.Config;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpFacade {
    static ObjectMapper objectMapper = new ObjectMapper();
    static Config config;

    static {
        try {
            config = objectMapper.readValue(new File("C:\\Users\\USER\\IdeaProjects\\Project\\Final-Project-Selenium\\ConfigFile.json"), Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public WrapApiResponse clearCartViaApi() throws IOException {
        URL url = new URL(config.getApiURL()+"v2/site/cart/delete");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(HttpMethod.DELETE.name());
        connection.setRequestProperty("Ecomtoken", config.getEcomToken());
        return getResponse(connection);
    }

    public static WrapApiResponse addItemToCartByIdViaApi(String itemId, String QTY) throws IOException {
        URL url = new URL(config.getApiURL()+"cart");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(HttpMethod.POST.name());
        connection.setRequestProperty("Ecomtoken", config.getEcomToken());
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoOutput(true);
        String body = "{\n" +
                "    \"supplyAt\":\""+getCurrentDate()+"\",\n" +
                "    \"items\":{\n" +
                "        \""+itemId+"\":\""+QTY+"\"\n" +
                "        }\n" +
                "}";
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        return getResponse(connection);
    }
    private static WrapApiResponse getResponse(HttpURLConnection connection) throws IOException {
        int status = connection.getResponseCode();
        String body = new String(connection.getInputStream().readAllBytes());
        Map<String, String> responseHeaders =
                connection.getHeaderFields().entrySet()
                        .stream()
                        .filter(entry -> entry.getKey() != null)
                        .collect(Collectors.toMap(Map.Entry::getKey, entry ->
                                entry.getValue().get(0)));
        connection.disconnect();
        return new WrapApiResponse(status, responseHeaders, body);
    }

    private static String getCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        return currentDate.format(formatter);
    }

}
