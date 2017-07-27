package com.blackbaud.events.api;

import com.blackbaud.rest.api.ApiEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements ApiEntity<Long> {

    private Long id;
    private BigDecimal basePrice;
    private Long eventId;

}
