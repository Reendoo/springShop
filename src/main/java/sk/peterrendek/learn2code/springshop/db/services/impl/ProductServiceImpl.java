package sk.peterrendek.learn2code.springshop.db.services.impl;

import org.springframework.stereotype.Service;
import sk.peterrendek.learn2code.springshop.db.repositories.ProductRepository;
import sk.peterrendek.learn2code.springshop.db.services.ProductService;
import sk.peterrendek.learn2code.springshop.db.services.request.UpdateProductRequest;
import sk.peterrendek.learn2code.springshop.domain.Product;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    public Product get(int id) {
        return productRepository.get(id);
    }

    @Override
    public Integer add(Product product) {
        return productRepository.add(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public void update(int id, UpdateProductRequest request) {
        productRepository.update(id, request);

    }
}
