package sk.peterrendek.learn2code.springshop.db.services.api;

import sk.peterrendek.learn2code.springshop.domain.BoughtProduct;

import java.util.List;

public interface BoughtProductService {
    void add(BoughtProduct boughtProduct);

    List<BoughtProduct> getAllCustomersById(int costumer_id);
}
