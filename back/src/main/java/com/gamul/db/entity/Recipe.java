package com.gamul.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xerces.internal.xs.StringList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import java.util.List;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe extends BaseEntity{

    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private String thumbnail;

    @Column(updatable = false)
    private String information;

    @Column(updatable = false)
    @Type(type= "com.iropke.common.hibernate.ArrayType")
    private List<String> order;

    @Column(updatable = false)
    @JsonProperty("image_order")
    @Type(type= "com.iropke.common.hibernate.ArrayType")
    private List<String> imageOrder;
}
