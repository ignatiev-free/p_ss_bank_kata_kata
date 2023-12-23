package com.bank.transfer.service;

import com.bank.transfer.entity.Audit;

import java.util.List;
import java.util.Optional;

public interface AuditService {

    List<Audit> getAll();
    Optional<Audit> getById(Long id);
    void save(Audit audit);

}
