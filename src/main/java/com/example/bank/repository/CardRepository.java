package com.example.bank.repository;

import com.example.bank.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByOwner(String owner);

    Optional<Card> findByCardNumber(String cardNumber);
}