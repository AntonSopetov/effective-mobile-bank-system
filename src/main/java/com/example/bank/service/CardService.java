package com.example.bank.service;

import com.example.bank.dto.CardDto;
import com.example.bank.entity.Card;
import com.example.bank.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService implements CardServiceInterface {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<CardDto> getCardsByOwner(String owner) {
        return cardRepository.findByOwner(owner).stream()
                .map(this::convertToDto)
                .peek(dto -> {
                    String num = dto.getCardNumber();
                    if (num != null && num.length() >= 4) {
                        dto.setCardNumber("**** **** **** " + num.substring(num.length() - 4));
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String transferMoney(String fromNumber, String toNumber, BigDecimal amount) {
        Card fromCard = cardRepository.findByCardNumber(fromNumber)
                .orElseThrow(() -> new RuntimeException("Отправитель не найден"));
        Card toCard = cardRepository.findByCardNumber(toNumber)
                .orElseThrow(() -> new RuntimeException("Получатель не найден"));

        if (fromCard.getBalance().compareTo(amount) < 0) {
            return "Недостаточно средств";
        }

        fromCard.setBalance(fromCard.getBalance().subtract(amount));
        toCard.setBalance(toCard.getBalance().add(amount));

        cardRepository.save(fromCard);
        cardRepository.save(toCard);
        return "Успешно";
    }

    @Override
    public String blockCard(String cardNumber) {
        Card card = cardRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> new RuntimeException("Карта не найдена"));
        card.setStatus("BLOCKED");
        cardRepository.save(card);
        return "Заблокировано";
    }

    @Override
    public CardDto saveCard(CardDto dto) {
        Card card = new Card();
        card.setCardNumber(dto.getCardNumber());
        card.setOwner(dto.getOwner());
        card.setBalance(dto.getBalance());
        card.setStatus("ACTIVE");
        return convertToDto(cardRepository.save(card));
    }

    private CardDto convertToDto(Card card) {
        CardDto dto = new CardDto();
        dto.setCardNumber(card.getCardNumber());
        dto.setOwner(card.getOwner());
        dto.setBalance(card.getBalance());
        dto.setStatus(card.getStatus());
        return dto;
    }
}
