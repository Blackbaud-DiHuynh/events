package com.blackbaud.events.api;

import com.blackbaud.rest.api.ApiEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements ApiEntity<Integer> {

    private Integer id;
    private BigDecimal basePrice;
    private Long eventId;
    private BigDecimal currentPrice;
    private Integer capacity;
}
