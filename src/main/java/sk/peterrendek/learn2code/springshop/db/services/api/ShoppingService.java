package sk.peterrendek.learn2code.springshop.db.services;

import sk.peterrendek.learn2code.springshop.db.services.api.request.BuyProductRequest;
import sk.peterrendek.learn2code.springshop.db.services.api.response.BuyProductResponse;

public interface ShoppingService {
    BuyProductResponse buyProduct(BuyProductRequest req);
}
