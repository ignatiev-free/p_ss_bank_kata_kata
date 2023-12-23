package com.bank.transfer.repository;

import com.bank.transfer.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * интерфейс для взаимодействия с базой данных и выполнения операций связанных с сущностью Audit.
 */

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {


}
