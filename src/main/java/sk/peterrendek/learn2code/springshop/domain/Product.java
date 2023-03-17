package sk.peterrendek.learn2code.springshop.domain;


import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class Product {

    @Nullable
    private Integer id;
    @NonNull
    private Integer merchant_id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private Double price;
    @Nullable
    private Timestamp createdAt;
    @NonNull
    private int available;

    public Product() {
    }

    public Product(@NonNull Integer merchant_id, @NonNull String name, @NonNull String description, @NonNull Double price, int available) {
        this.merchant_id = merchant_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.createdAt= Timestamp.from(Instant.now());
    }

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(@Nullable Integer id) {
        this.id = id;
    }

    @NonNull
    public Integer getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(@NonNull Integer merchant_id) {
        this.merchant_id = merchant_id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public Double getPrice() {
        return price;
    }

    public void setPrice(@NonNull Double price) {
        this.price = price;
    }

    @Nullable
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return available == product.available
                && Objects.equals(id, product.id)
                && merchant_id.equals(product.merchant_id)
                && name.equals(product.name)
                && description.equals(product.description)
                && price.equals(product.price)
                && createdAt.getTime()== product.createdAt.getTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, merchant_id, name, description, price, createdAt, available);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", merchant_id=" + merchant_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                ", available=" + available +
                '}';
    }
}
