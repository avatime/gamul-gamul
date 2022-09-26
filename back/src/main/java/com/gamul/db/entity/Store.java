package com.gamul.db.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter
@NoArgsConstructor

public class Store extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column
    private double latitude;

    @Column
    private double longitude;


    @Builder
    public Store(String name, double latitude, double longitude, int type, int doId, int siId){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
