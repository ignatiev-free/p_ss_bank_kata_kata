package com.bank.transfer.service;

import com.bank.transfer.entity.Phone;

import java.util.List;
import java.util.Optional;
import com.bank.transfer.aop.DeleteToLog;
import com.bank.transfer.repository.PhoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<Phone> getAll() {
        log.info("try to get All phones");
        var getAllPhones = phoneRepository.findAll();
        log.info("getAll phones success, count = {}", getAllPhones.size());
        return getAllPhones;
    }

    @Override
    public Optional<Phone> getById(Long id) {
        log.info("try to get phone by id = {}", id);
        var getByIdPhone = phoneRepository.findById(id);
        log.info("getById phone success, getByIdPhone.isPresent() = {}",
                getByIdPhone.isPresent());
        return getByIdPhone;
    }

    @Override
    @Transactional
    public void save(Phone phone) {
        log.info("try to save phone = {}", phone);
        phoneRepository.save(phone);
        log.info("save phone success, id = {}", phone.getId());
    }

    @Override
    @Transactional
    public void update(Long id, Phone phoneTransferEntity) {
        phoneTransferEntity.setId(id);
        log.info("try to update phone = {}", phoneTransferEntity);
        phoneRepository.save(phoneTransferEntity);
        log.info("update phone success, id = {}", phoneTransferEntity.getId());
    }

    @Override
    @Transactional
    @DeleteToLog
    public void delete(Long id) {
        log.info("try to delete phone with id = {}", id);
        phoneRepository.deleteById(id);
        log.info("success delete phone with id = {}", id);
    }

    @Override
    public Optional<Phone> getByPhoneNumber(Long phoneNumber) {
        log.info("try to get phone by phoneNumber = {}", phoneNumber);
        var getByNumberPhoneTransferEntity = phoneRepository.getByPhoneNumber(phoneNumber);
        log.info("getByPhoneNumber phone success, getByNumberPhone.isPresent() = {}",
                getByNumberPhoneTransferEntity.isPresent());
        return getByNumberPhoneTransferEntity;
    }
}
