package sk.peterrendek.learn2code.springshop.db.services.api.request;

import lombok.*;


import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor

public class UpdateProductRequest {
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private Double price;
    @NonNull
    private Integer available;
}
