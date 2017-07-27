package com.blackbaud.events.api;

import com.blackbaud.rest.api.ApiEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class DynamicRule implements ApiEntity<Integer> {

    private Integer id;

    private BigDecimal priceChange;

    private Integer ticketId;

    private Integer inventoryThreshold;

    private DynamicRuleType type;
}
