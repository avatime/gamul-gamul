package com.gamul.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@DynamicInsert
@DynamicUpdate
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseEntity {

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private IngredientPriceNotice ingredientPriceNotice;

    @Column
    private boolean type;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("created_time")
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    public Notice(IngredientPriceNotice ingredientPriceNotice, boolean type) {
        this.ingredientPriceNotice = ingredientPriceNotice;
        this.type = type;
    }

    @PrePersist
    public void onCreate() {
        this.createdTime = Timestamp.valueOf(LocalDateTime.now());
    }

}
