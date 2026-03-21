package com.example.bank;

import com.example.bank.service.CardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CardServiceTest {
    @Autowired
    private CardService cardService;

    @Test
    void contextLoads() {
        assertNotNull(cardService);
    }
}
