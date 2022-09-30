package com.gamul.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.common.util.JsonToMapConverter;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 유저 모델 정의
 */
@DynamicInsert
@DynamicUpdate
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(nullable = false, unique = true, length = 10)
    private String username;

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    @JsonProperty("refresh_token")
    @Column
    private String refreshToken;

    @JsonProperty("subscription")
    @Column(name = "subscription", columnDefinition = "json")
    @Convert(attributeName = "data", converter = JsonToMapConverter.class)
    private Map<String, Object> subscription = new HashMap<>();

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
    public User(String username, String password, MyRecipe myRecipe){
        this.username = username;
        this.password = password;
    }
}
