package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import jdk.nashorn.api.scripting.JSObject;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
@ApiModel("OnlineIngredientInfo")
public class OnlineIngredientInfoRes {

    String name;

    int price;

    @JsonProperty("image_path")
    String imagePath;

    @JsonProperty("online_store")
    String onlineStore;

    public OnlineIngredientInfoRes(JSONObject itemJson){
        this.name = itemJson.getString("title");
        this.price = itemJson.getInt("lprice");
        this.imagePath = itemJson.getString("mallName");
        this.onlineStore = itemJson.getString("link");
    }
}



