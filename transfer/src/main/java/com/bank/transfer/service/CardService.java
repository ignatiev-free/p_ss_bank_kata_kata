package com.bank.transfer.service;

import com.bank.transfer.entity.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {

    List<Card> getAll();
    Optional<Card> getById(Long id);
    Optional<Card> getByCardNumber(Long cardNumber);
    void save(Card card);
    void update(Long id, Card card);
    void delete(Long id);

}
