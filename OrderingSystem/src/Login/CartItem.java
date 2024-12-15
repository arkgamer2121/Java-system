package Login;

import java.math.BigDecimal;

public class CartItem {
    private String itemName;
    private BigDecimal price;
    private int quantity;

    @SuppressWarnings("deprecation")
	public CartItem(String itemName, String price, int quantity) {
        this.itemName = itemName;
 
        this.price = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.quantity = quantity;
    }

   
    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
       
        return price.doubleValue();
    }

    public int getQuantity() {
        return quantity;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @SuppressWarnings("deprecation")
	public void setPrice(String price) {
        this.price = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

 
    @Override
    public String toString() {
        return String.format("Item: %s, Price: %s, Quantity: %d", itemName, price.toString(), quantity);
    }

 
    @SuppressWarnings("deprecation")
	public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(quantity)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
