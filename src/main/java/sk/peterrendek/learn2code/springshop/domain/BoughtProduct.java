package sk.peterrendek.learn2code.springshop.domain;

import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class BoughtProduct {
    private int product_id;
    private int customer_id;
    private int quantity;
    private Timestamp boughtAt;

    public BoughtProduct(int product_id, int customer_id, int quantity) {
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.quantity = quantity;
        this.boughtAt = Timestamp.from(Instant.now());
    }
}
