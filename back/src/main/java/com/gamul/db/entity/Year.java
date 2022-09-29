package com.gamul.db.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
public class Year extends BaseEntity {
    @Column
    private String datetime;

    @Column
    private String midclass;

    @Column
    private int price;
}
