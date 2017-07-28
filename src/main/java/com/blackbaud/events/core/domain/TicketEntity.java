package com.blackbaud.events.core.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity(name = "ticket")
@Table(name = "ticket")
@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticket_seq_gen")
    @SequenceGenerator(name = "ticket_seq_gen", sequenceName = "ticket_seq")
    private int id;
    private BigDecimal basePrice;
    private Integer eventId;
    private int capacity;
}
