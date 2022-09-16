package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("OnlineMartInfoRes")
public class OnlineMartInfoRes {
    @JsonProperty("image_path")
    String imagePath;

    String name;

    int price;

    String url;
}
