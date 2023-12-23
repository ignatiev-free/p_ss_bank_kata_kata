package com.bank.transfer.validator;

import com.bank.transfer.entity.Card;
import com.bank.transfer.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class CardNumberUniqueValidator implements Validator {

    private final CardService cardService;

    public CardNumberUniqueValidator(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public boolean supports(Class<?> classify) {
        return Card.class.equals(classify);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Card cardTransferEntity = (Card) target;
        log.info("try to validate cardTransferEntity: {}", cardTransferEntity);

        cardService.getByCardNumber(cardTransferEntity.getCardNumber())
                .ifPresent((value) -> validateCardNumber(cardTransferEntity, value, errors)
                );
        log.info("success validate cardTransferEntity: {}", cardTransferEntity);
    }

    private void validateCardNumber(Card validated, Card fromDataBase, Errors errors) {
        if (!compareTransfer(validated, fromDataBase)) {
            errors.rejectValue(
                    "cardNumber",
                    String.format("cardNumber %s already exist!", fromDataBase.getCardNumber()),
                    String.format("cardNumber %s already exist!", fromDataBase.getCardNumber()));
        }
    }

    private boolean compareTransfer(Card validated, Card fromDataBase) {
        if (validated.getId() == null) {
            return false;
        }
        return validated.getId().equals(fromDataBase.getId());
    }

}

