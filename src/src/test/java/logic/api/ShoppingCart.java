package logic.api;
import infra.api.HttpFacade;
import java.io.IOException;

public class ShoppingCart {

    public void addItemToCartViaApi(String itemId, String QTY) throws IOException {
        HttpFacade.addItemToCartByIdViaApi(itemId,QTY);
    }
    public void clearCartViaApi() throws IOException {
        HttpFacade.clearCartViaApi();
    }
}
