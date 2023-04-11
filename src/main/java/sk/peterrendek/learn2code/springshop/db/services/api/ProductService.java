package sk.peterrendek.learn2code.springshop.db.services.api;

import org.springframework.lang.Nullable;
import sk.peterrendek.learn2code.springshop.db.services.api.request.UpdateProductRequest;
import sk.peterrendek.learn2code.springshop.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    @Nullable
    Product get(int id);
    @Nullable
    Integer add(Product product);
    void delete(int id);
    void update(int id, UpdateProductRequest request);
    void updateAvailableInternal(int id, int newAvailable);

}
