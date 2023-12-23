package com.bank.transfer.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "phone_transfer", schema = "transfer")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "account_details_id", nullable = false)
    private Long accountDetailsId;

}
