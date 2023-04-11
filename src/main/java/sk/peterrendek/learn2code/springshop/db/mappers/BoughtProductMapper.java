package sk.peterrendek.learn2code.springshop.db.mappers;

import org.springframework.jdbc.core.RowMapper;
import sk.peterrendek.learn2code.springshop.domain.BoughtProduct;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BoughtProductMapper implements RowMapper<BoughtProduct> {
    @Override
    public BoughtProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        BoughtProduct boughtProduct = new BoughtProduct();
        boughtProduct.setProduct_id(rs.getInt("product_id"));
        boughtProduct.setCustomer_id(rs.getInt("customer_id"));
        boughtProduct.setQuantity(rs.getInt("quantity"));
        boughtProduct.setBoughtAt(rs.getTimestamp("bought_at"));
        return boughtProduct;
    }
}
