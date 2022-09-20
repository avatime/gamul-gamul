package com.gamul.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeImage extends BaseEntity{
    @Column
    @JsonProperty("image_path")
    String imagePath;

    @Column
    @JsonProperty("image_order")
    int imageOrder;

    @ManyToOne
    private MyRecipe myRecipe;
}
