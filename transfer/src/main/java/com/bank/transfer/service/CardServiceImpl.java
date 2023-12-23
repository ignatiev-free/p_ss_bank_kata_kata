package com.bank.transfer.service;

import com.bank.transfer.aop.DeleteToLog;
import com.bank.transfer.entity.Card;
import com.bank.transfer.repository.CardRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getAll() {
        log.info("try to get All Cards");
        var getAllCardEntities = cardRepository.findAll();
        log.info("getAll Cards success, count = {}",
                getAllCardEntities.size());
        return getAllCardEntities;
    }

    @Override
    public Optional<Card> getById(Long id) {
        log.info("try to get Card by ID = {}", id);
        var getByIdCardEntity = cardRepository.findById(id);
        log.info("getById Card success, CardById = {}", getByIdCardEntity.isPresent());
        return getByIdCardEntity;
    }

    @Override
    public Optional<Card> getByCardNumber(Long cardNumber) {
        log.info("try to get cardTransferEntity by cardNumber = {}", cardNumber);
        var getByNumberCardTransferEntity = cardRepository.getByCardNumber(cardNumber);
        log.info("getByNumber cardTransferEntity success, transfer.isPresent() = {}",
                getByNumberCardTransferEntity.isPresent());
        return getByNumberCardTransferEntity;
    }

    @Override
    @Transactional
    public void save(Card card) {
        log.info("try to save cardTransferEntity = {}", card);
        cardRepository.save(card);
        log.info("save cardTransferEntity success, id = {}", card.getId());
    }

    @Override
    @Transactional
    public void update(Long id, Card cardTransferEntity) {
        cardTransferEntity.setId(id);
        log.info("try to update cardTransferEntity = {}", cardTransferEntity);
        cardRepository.save(cardTransferEntity);
        log.info("update cardTransferEntity success, id = {}", cardTransferEntity.getId());
    }

    @Override
    @Transactional
    @DeleteToLog
    public void delete(Long id) {
        log.info("try to delete cardTransferEntity with id = {}", id);
        cardRepository.deleteById(id);
        log.info("success delete cardTransferEntity with id = {}", id);
    }
}
