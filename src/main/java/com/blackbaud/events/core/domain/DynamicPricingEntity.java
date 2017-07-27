package com.blackbaud.events.core.domain;

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

@Entity(name = "dynamic_pricing")
@Table(name = "dynamic_pricing")
@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DynamicPricingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dynamic_pricing_seq_gen")
    @SequenceGenerator(name = "dynamic_pricing_seq_gen", sequenceName = "dynamic_pricing_seq")
    private Integer id;

    private BigDecimal priceChange;

    private Integer ticketId;

    private Integer remainingInventory;

    @Enumerated(EnumType.STRING)
    private DynamicPricingType type;

}
