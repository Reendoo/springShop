package sk.peterrendek.learn2code.springshop.domain;

import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@EqualsAndHashCode
public class Product {
    @EqualsAndHashCode.Exclude
    private Timestamp createdAt;
    private Integer id;
    @NonNull
    private Integer merchant_id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private Double price;
    @NonNull
    private Integer available;

    public Product(@NonNull Integer merchant_id, @NonNull String name, @NonNull String description, @NonNull Double price, int available) {
        this.merchant_id = merchant_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.createdAt = Timestamp.from(Instant.now());
    }
}
