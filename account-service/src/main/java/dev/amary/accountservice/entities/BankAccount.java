package dev.amary.accountservice.entities;

import dev.amary.accountservice.enums.AccountType;
import dev.amary.accountservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Setter @Getter @ToString @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class BankAccount {
    @Id
    private String accountId;
    private double balance;
    private LocalDate createdAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Transient
    private Customer customer;
    private Long customerId;

}
