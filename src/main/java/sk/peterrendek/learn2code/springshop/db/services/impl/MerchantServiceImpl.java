package sk.peterrendek.learn2code.springshop.db.services.impl;

import org.springframework.stereotype.Service;
import sk.peterrendek.learn2code.springshop.db.repositories.MerchantRepository;
import sk.peterrendek.learn2code.springshop.db.services.MerchantService;
import sk.peterrendek.learn2code.springshop.domain.Merchant;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
    MerchantRepository repository;

    public MerchantServiceImpl(MerchantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Merchant> getAllMerchants() {
       return repository.getAll();
    }

    @Override
    public Merchant get(int id) {
        return repository.get(id);
    }

    @Override
    public Integer add(Merchant merchant) {
       return repository.add(merchant);
    }
}
