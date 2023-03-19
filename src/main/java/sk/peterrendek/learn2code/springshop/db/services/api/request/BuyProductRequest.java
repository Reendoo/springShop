package sk.peterrendek.learn2code.springshop.db.services.request;

import java.util.Objects;

public class BuyProductRequest {
    private int product_id;
    private int customer_id;
    private int quantity;

    public BuyProductRequest(int product_id, int customer_id, int quantity) {
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.quantity = quantity;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyProductRequest that = (BuyProductRequest) o;
        return product_id == that.product_id && customer_id == that.customer_id && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, customer_id, quantity);
    }
}
