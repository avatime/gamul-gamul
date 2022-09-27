package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
@ApiModel("OnlineMartInfoRes")
public class OnlineMartInfoRes {
    @JsonProperty("image_path")
    String imagePath;

    String name;

    int price;

    String url;

    public OnlineMartInfoRes(JSONObject itemJson){
        this.name = itemJson.getString("title");
        this.price = itemJson.getInt("lprice");
        this.imagePath = itemJson.getString("image");
        this.url = itemJson.getString("mallName");
    }
}
