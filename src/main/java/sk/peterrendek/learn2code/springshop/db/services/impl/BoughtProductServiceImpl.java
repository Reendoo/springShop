package sk.peterrendek.learn2code.springshop.db.services.impl;

import org.springframework.stereotype.Service;
import sk.peterrendek.learn2code.springshop.db.repositories.BoughtProductRepository;
import sk.peterrendek.learn2code.springshop.db.services.BoughtProductService;
import sk.peterrendek.learn2code.springshop.domain.BoughtProduct;

import java.util.List;
@Service
public class BoughtProductServiceImpl implements BoughtProductService {
    BoughtProductRepository repository;

    public BoughtProductServiceImpl(BoughtProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(BoughtProduct boughtProduct) {
    repository.add(boughtProduct);
    }

    @Override
    public List<BoughtProduct> getAllCustomersById(int costumer_id) {
        return repository.getAllCustomersById(costumer_id);
    }
}
