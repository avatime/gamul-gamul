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
@Setter
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

    @Column(columnDefinition = "INT UNSIGNED")
    private long views;

}
