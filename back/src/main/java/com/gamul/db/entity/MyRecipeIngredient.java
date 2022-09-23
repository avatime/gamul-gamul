package com.gamul.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.models.auth.In;
import lombok.*;
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
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyRecipeIngredient extends BaseEntity{

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MyRecipe myRecipe;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ingredient ingredient;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("created_time")
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @PrePersist
    public void onCreate() {
        this.createdTime = Timestamp.valueOf(LocalDateTime.now());
    }

    @Builder
    public MyRecipeIngredient (MyRecipe myRecipe, Ingredient ingredient){
        this.myRecipe = myRecipe;
        this.ingredient = ingredient;
    }
}
