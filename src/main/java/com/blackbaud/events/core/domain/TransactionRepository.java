package com.blackbaud.events.core.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findByTicketIdIn(List<Integer> ticketIds);

    List<TransactionEntity> findByTicketId(Integer ticketId);
}
