package com.gamul.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
public class Ingredient extends BaseEntity{
    @JsonProperty("high_class")
    @Column(updatable = false, nullable = false)
    private int highClass;

    @JsonProperty("mid_class")
    @Column(updatable = false, nullable = false)
    private String midClass;

    @JsonProperty("low_class")
    @Column(updatable = false, nullable = false)
    private String lowClass;

    @Column(updatable = false, nullable = false)
    private int type;

    @Column(columnDefinition = "INT UNSIGNED")
    private long views;

    @Builder
    public Ingredient(int highClass, String midClass, String lowClass, int type){
        this.highClass = highClass;
        this.midClass = midClass;
        this.lowClass = lowClass;
        this.type = type;
        this.views = 0;
    }

}
