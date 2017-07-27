package com.blackbaud.events.core.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DynamicRuleRepository extends CrudRepository<DynamicRuleEntity, Integer> {
    List<DynamicRuleEntity> findByTicketId(Integer id);
}
