package com.example.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cardNumber;

    @NotBlank(message = "Владелец не может быть пустым")
    @Column(nullable = false)
    private String owner;

    private LocalDate expirationDate;

    @Column(nullable = false)
    private String status;

    @PositiveOrZero(message = "Баланс не может быть отрицательным")
    @Column(nullable = false)
    private BigDecimal balance;
}
