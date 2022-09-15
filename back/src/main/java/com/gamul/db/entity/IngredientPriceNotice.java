package com.gamul.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientPriceNotice extends BaseEntity{

    @Column(nullable = false)
    private boolean activeFlag;

    @Column
    private int upperLimitPrice;

    @Column
    private int lowerLimitPrice;

    @ManyToOne
    private User user;

    @ManyToOne
    private Ingredient ingredient;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("created_time")
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("update_time")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updateTime;

    @PrePersist
    public void onCreate() {
        this.createdTime = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate() {
        this.updateTime = Timestamp.valueOf(LocalDateTime.now());
    }

    @Builder
    public IngredientPriceNotice(User user, Ingredient ingredient) {
        this.activeFlag = true;
        this.user = user;
        this.ingredient = ingredient;
    }

}
