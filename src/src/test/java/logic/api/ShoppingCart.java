package logic.api;
import infra.api.HttpFacade;
import java.io.IOException;

public class ShoppingCart {

    public void addItemtToCartViaApi(String itemId, String QTY) throws IOException {
        HttpFacade.addItemToCartByIdViaApi(itemId,QTY);
    }
}
