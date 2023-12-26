package logic.ui;

public class Item {
    private String name;
    private String price;
    private String quantity;
    private String barcode;

    public Item(String name, String price, String quantity, String barcode) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}
