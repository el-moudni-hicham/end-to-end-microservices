package dev.moudni.accountservice.entites;

import dev.moudni.accountservice.enums.AccountType;
import dev.moudni.accountservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id
    private String accountId;
    private String currency;
    private double balance;
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private Long customerId;
    @Transient //ignore this don't store it in database
    private Customer customer;
}
