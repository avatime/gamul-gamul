package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("HighClassNameRes")
public class HighClassNameRes {
    @JsonProperty("high_class_id")
    int highClassId;

    String name;
}
