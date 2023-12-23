package com.bank.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.transfer.entity.Phone;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * интерфейс для взаимодействия с базой данных и выполнения операций связанных с сущностью Phone.
 */

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    Optional<Phone> getByPhoneNumber(Long phoneNumber);

    Optional<Phone> findById(Long id);

    void deleteById(Long id);
}
