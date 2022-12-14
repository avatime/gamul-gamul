package com.gamul.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@DynamicInsert
@DynamicUpdate
@Entity(name = "entire")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Entire extends BaseEntity {

    @Column(updatable = false, nullable = false)
    private String dateTime;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private Double quantity;

    @Column(updatable = false, nullable = false)
    private int price;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ingredient ingredient;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Store store;

    @Column(updatable = false, nullable = false)
    private int type;

    @Builder
    public Entire(Date dateTime, String unit, Double quantity, int price, Store store, int type){
        this.dateTime = dateTime.toString();
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.store = store;
        this.type = type;
    }
}
