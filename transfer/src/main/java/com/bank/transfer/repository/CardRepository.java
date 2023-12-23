package com.bank.transfer.repository;

import com.bank.transfer.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * интерфейс для взаимодействия с базой данных и выполнения операций связанных с сущностью Card.
 */

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> getByCardNumber(Long cardNumber);

}
