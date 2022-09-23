package com.gamul.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class MyRecipe extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column
    @JsonProperty("image_url")
    private String imageURL;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("created_time")
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("update_time")
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @PrePersist
    public void onCreate() {
        this.createdTime = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate() {
        this.updateTime = Timestamp.valueOf(LocalDateTime.now());
    }

    @Builder
    public MyRecipe(String name, String imageURL, User user){
        this.name = name;
        this.imageURL = imageURL;
        this.user = user;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
