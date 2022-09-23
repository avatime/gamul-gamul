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
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Price extends BaseEntity{
    @Column(updatable = false, nullable = false)
    private Date dateTime;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private int quantity;

    @Column(updatable = false, nullable = false)
    private int price;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ingredient ingredient;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Store store;

    @Builder
    public Price(Date dateTime, String unit, int quantity, int price, Store store){
        this.dateTime = dateTime;
        this.unit = unit;
        this.quantity = quantity;
        this.price = price;
        this.store = store;
    }
}
