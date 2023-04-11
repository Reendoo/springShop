package sk.peterrendek.learn2code.springshop.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(force = true)
@EqualsAndHashCode
public class CustomerAccount {
    private int customerId;
    private double money;
}
