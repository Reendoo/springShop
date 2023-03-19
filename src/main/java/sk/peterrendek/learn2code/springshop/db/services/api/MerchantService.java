package sk.peterrendek.learn2code.springshop.db.services;


import org.springframework.lang.Nullable;
import sk.peterrendek.learn2code.springshop.domain.Merchant;

import java.util.List;

public interface MerchantService {
    List<Merchant> getAllMerchants();
    @Nullable
    Merchant get(int id);
    @Nullable
    Integer add (Merchant merchant); //generated id

}
