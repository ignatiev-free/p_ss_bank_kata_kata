package com.bank.transfer.service;

import com.bank.transfer.entity.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneService {

    List<Phone> getAll();
    Optional<Phone> getById(Long id);
    void save(Phone phone);
    void update(Long id, Phone phone);
    void delete(Long id);
    Optional<Phone> getByPhoneNumber(Long phoneNumber);
}
