package sk.peterrendek.learn2code.springshop.db.services.api.response;

import lombok.*;
import org.springframework.lang.Nullable;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode

public class BuyProductResponse {
    private boolean succes;
    @Nullable
    private String errorMessage;

    public BuyProductResponse(boolean succes) {
        this.succes = succes;
    }

    public BuyProductResponse(boolean succes, @Nullable String errorMessage) {
        this.succes = succes;
        this.errorMessage = errorMessage;
    }

}
