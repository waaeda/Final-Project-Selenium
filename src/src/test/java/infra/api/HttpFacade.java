package infra.api;

import infra.Config;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.impl.client.HttpClients;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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

    public static WrapApiResponse clearCartViaApi() throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPatch httpPatch = new HttpPatch("https://www-api.rami-levy.co.il/api/v2/site/cart/delete");
        httpPatch.setHeader("accept", "application/json");
        httpPatch.setHeader("Content-Type", "application/json");
        httpPatch.setHeader("Ecomtoken", config.getEcomToken());
        HttpResponse response = httpClient.execute(httpPatch);
        int status = response.getStatusLine().getStatusCode();
        String body = response.getStatusLine().toString();
        Map<String, String> responseHeaders = new HashMap<>();
        return new WrapApiResponse(status, responseHeaders, body);
    }

    public static WrapApiResponse addItemToCartByIdViaApi(String itemId, String QTY) throws IOException {
        URL url = new URL(config.getApiURL()+"cart");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(HttpMethod.POST.name());
        connection.setRequestProperty("Ecomtoken", config.getEcomToken());
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
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

        return new WrapApiResponse(status, responseHeaders, body);
    }

    private static String getCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        return currentDate.format(formatter);
    }

}