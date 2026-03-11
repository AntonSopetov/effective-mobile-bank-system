package com.example.bank.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String owner;

    private LocalDate expirationDate;

    @Column(nullable = false)
    private String status; // "ACTIVE", "BLOCKED", "EXPIRED"

    @Column(nullable = false)
    private BigDecimal balance;
}
