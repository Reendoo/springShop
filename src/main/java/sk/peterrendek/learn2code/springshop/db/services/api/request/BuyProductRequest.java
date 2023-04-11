package sk.peterrendek.learn2code.springshop.db.services.api.request;

public record BuyProductRequest(int product_id, int customer_id, int quantity) {
}
