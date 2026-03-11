package com.example.bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(com.example.bank.repository.CardRepository repository) {
        return args -> {
            com.example.bank.entity.Card card = new com.example.bank.entity.Card();
            card.setCardNumber("1234567812345678");
            card.setOwner("Anton");
            card.setBalance(java.math.BigDecimal.valueOf(1000));
            card.setStatus("ACTIVE");
            repository.save(card);
        };
    }
}
