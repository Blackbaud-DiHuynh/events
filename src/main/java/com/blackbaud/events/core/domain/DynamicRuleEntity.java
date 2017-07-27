package com.blackbaud.events.core.domain;

import com.blackbaud.events.api.DynamicRuleType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity(name = "dynamic_rule")
@Table(name = "dynamic_rule")
@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DynamicRuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dynamic_rule_seq_gen")
    @SequenceGenerator(name = "dynamic_rule_seq_gen", sequenceName = "dynamic_rule_seq")
    private Integer id;

    private BigDecimal priceChange;

    private Integer ticketId;

    private Integer inventoryThreshold;

    @Enumerated(EnumType.STRING)
    private DynamicRuleType type;
}
