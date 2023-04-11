package sk.peterrendek.learn2code.springshop.db.services.impl;

import org.springframework.stereotype.Service;
import sk.peterrendek.learn2code.springshop.db.repositories.ProductRepository;
import sk.peterrendek.learn2code.springshop.db.services.api.ProductService;
import sk.peterrendek.learn2code.springshop.db.services.api.request.UpdateProductRequest;
import sk.peterrendek.learn2code.springshop.domain.Product;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.repository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return repository.getAll();
    }

    @Override
    public Product get(int id) {
        return repository.get(id);
    }

    @Override
    public Integer add(Product product) {
        return repository.add(product);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void update(int id, UpdateProductRequest request) {
        repository.update(id, request);
    }

    @Override
    public void updateAvailableInternal(int id, int newAvailable) {
        repository.updateAvailable(id,newAvailable);
    }
}
