package com.jj.ticketseller.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;


@Entity
@Getter
public class Show {
    @Id @GeneratedValue()
    private Long id;

    private String name;

    private Integer price;

    @OneToOne(mappedBy = "show")
    private Wait wait;
}
