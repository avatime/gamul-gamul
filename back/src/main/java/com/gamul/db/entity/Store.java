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
@RequiredArgsConstructor
public class Store extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Column(nullable = false)
    private int type;

    @OneToOne
    @JoinColumn(name = "do_id")
    private Do dou;

    @OneToOne
    @JoinColumn(name = "sigugun_id")
    private Sigugun sigugun;

    @Builder
    public Store(String name, double latitude, double longitude, int type, Do dou, Sigugun sigugun){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.dou = dou;
        this.sigugun = sigugun;
    }
}
