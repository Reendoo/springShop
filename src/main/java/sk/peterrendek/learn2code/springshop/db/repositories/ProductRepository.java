package sk.peterrendek.learn2code.springshop.db.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import sk.peterrendek.learn2code.springshop.db.mappers.ProductRowMapper;
import sk.peterrendek.learn2code.springshop.db.services.api.request.UpdateProductRequest;
import sk.peterrendek.learn2code.springshop.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Component
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProductRowMapper productRowMapper = new ProductRowMapper();

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product get(int id) {
        final String sql = "select * from product where product.id=" + id;
        try {
            return jdbcTemplate.queryForObject(sql, productRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Integer add(Product product) {
        final String sql = "insert into product(merchant_id,name,description,price,created_at,available) values(?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pr = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pr.setInt(1, product.getMerchant_id());
            pr.setString(2, product.getName());
            pr.setString(3, product.getDescription());
            pr.setDouble(4, product.getPrice());
            if (product.getCreatedAt() == null) {
                product.setCreatedAt(Timestamp.from(Instant.now()));
            }
            pr.setTimestamp(5, product.getCreatedAt());
            pr.setInt(6, product.getAvailable());
            return pr;
        }, keyHolder);
        if (keyHolder.getKey() != null) {
            return keyHolder.getKey().intValue();
        } else {
            return null;
        }
    }

    public List<Product> getAll() {
        final String sql = "select * from product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    public void update(int id, UpdateProductRequest req) {
        final String sql = "update product set name=?, description=?,price=?,available=? where id=?";
        jdbcTemplate.update(sql, req.getName(), req.getDescription(), req.getPrice(), req.getAvailable(), id);
    }

    public void delete(int id) {
        final String sql = "delete from product where id =?";
        jdbcTemplate.update(sql, id);
    }

    public void updateAvailable(int id, int newAvailable) {
        final String sql = "update product set available = ? where id = ?";
        jdbcTemplate.update(sql, newAvailable, id);
    }
}
