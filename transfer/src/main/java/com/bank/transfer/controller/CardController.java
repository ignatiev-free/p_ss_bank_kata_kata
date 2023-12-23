package com.bank.transfer.controller;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.Audit;
import com.bank.transfer.exception.CardEntityNotFoundException;
import com.bank.transfer.exception.CardValidatorException;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.service.AuditService;
import com.bank.transfer.service.CardService;
import com.bank.transfer.utils.Utils;
import com.bank.transfer.validator.CardNumberUniqueValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/card_transfers")
public class CardController {

    private final CardService cardService;
    private final CardNumberUniqueValidator cardTransferValidator;
    private final AuditService auditService;

    public CardController(CardService cardService,
                                  CardNumberUniqueValidator cardValidator,
                                  AuditService auditService) {
        this.cardService = cardService;
        this.cardTransferValidator = cardValidator;
        this.auditService = auditService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardTransferDto>> getAll() {
        var dtoList = cardService.getAll()
                .stream()
                .map(CardTransferMapper.CARD_TRANSFER_MAPPER::entityToDtoCardTransfer)
                .collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardTransferDto> getById(@PathVariable("id") Long id) {
        var cardTransferEntity =
                cardService
                        .getById(id)
                        .orElseThrow(() -> new CardEntityNotFoundException(String
                                .format("cardTransferEntity with id = %d not found", id)));
        var dtoCardTransfer = CardTransferMapper.CARD_TRANSFER_MAPPER.entityToDtoCardTransfer(cardTransferEntity);
        return new ResponseEntity<>(dtoCardTransfer, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardTransferDto> create(@RequestBody @Valid CardTransferDto cardTransferDto, BindingResult bindingResult) {
        var cardTransferEntity = CardTransferMapper.CARD_TRANSFER_MAPPER.dtoToEntityCardTransfer(cardTransferDto);
        cardTransferValidator.validate(cardTransferEntity, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new CardValidatorException(Utils.getMassageOfError(bindingResult));
        }
        cardService.save(cardTransferEntity);
        Audit auditEntity = new Audit();
        auditEntity.setEntityType("CardTransferEntity");
        auditEntity.setOperationType("SAVE");
        auditEntity.setEntityJson(cardTransferEntity.toString());
        auditEntity.setNewEntityJson(cardTransferEntity.toString());
        auditService.save(auditEntity);
        var savedDto = CardTransferMapper.CARD_TRANSFER_MAPPER.entityToDtoCardTransfer(cardTransferEntity);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardTransferDto> update(@PathVariable("id") Long id,
                                                  @RequestBody @Valid CardTransferDto cardTransferDto,
                                                  BindingResult bindingResult) {
        var oldTransfer = cardService.getById(id)
                .orElseThrow(() -> new CardEntityNotFoundException(String
                        .format("cardTransferEntity with id = %d not found", id)));
        var cardTransferEntity = CardTransferMapper.CARD_TRANSFER_MAPPER.dtoToEntityCardTransfer(cardTransferDto);
        cardTransferEntity.setId(id);
        cardTransferValidator.validate(cardTransferEntity, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new CardValidatorException(Utils.getMassageOfError(bindingResult));
        }
        cardService.update(id, cardTransferEntity);
        Audit auditEntity = new Audit();
        auditEntity.setEntityType("CardTransfer");
        auditEntity.setOperationType("PUT_UPDATE");
        auditEntity.setNewEntityJson(cardTransferEntity.toString());
        auditEntity.setEntityJson(oldTransfer.toString());
        auditService.save(auditEntity);
        var updatedDto = CardTransferMapper.CARD_TRANSFER_MAPPER.entityToDtoCardTransfer(cardTransferEntity);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        var oldTransfer = cardService.getById(id)
                .orElseThrow(() -> new CardEntityNotFoundException(String
                        .format("cardTransferEntity with id = %d not found", id)));
        cardService.delete(id);
        Audit auditEntity = new Audit();
        auditEntity.setEntityType("CardTransfer");
        auditEntity.setOperationType("DELETE");
        auditEntity.setNewEntityJson(oldTransfer.toString());
        auditEntity.setEntityJson(oldTransfer.toString());
        auditService.save(auditEntity);
    }

}