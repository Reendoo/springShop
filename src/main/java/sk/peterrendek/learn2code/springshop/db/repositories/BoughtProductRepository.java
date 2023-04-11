package sk.peterrendek.learn2code.springshop.db.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sk.peterrendek.learn2code.springshop.db.mappers.BoughtProductMapper;
import sk.peterrendek.learn2code.springshop.domain.BoughtProduct;


import java.util.List;

@Component
public class BoughtProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final BoughtProductMapper boughtProductMapper = new BoughtProductMapper();

    public BoughtProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(BoughtProduct boughtProduct) {
        final String sql = "insert into bought_product(product_id, customer_id,quantity,bought_at) values(?,?,?,?)";
        jdbcTemplate.update(sql, boughtProduct.getProduct_id(), boughtProduct.getCustomer_id()
                , boughtProduct.getQuantity(), boughtProduct.getBoughtAt());
    }

    public List<BoughtProduct> getAllCustomersById(int customer_id) {
        final String sql = "select * from bought_product where customer_id = ?";
        return jdbcTemplate.query(sql, boughtProductMapper, customer_id);
    }
}
