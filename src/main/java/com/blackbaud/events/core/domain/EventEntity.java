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
import java.util.Date;

@Entity(name = "event")
@Table(name = "event")
@Data
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "event_seq_gen")
    @SequenceGenerator(name = "event_seq_gen", sequenceName = "event_seq")
    private int id;

    private Date date;

    private Date time;

    private String location;

    private int capacity;

    private String name;
}
