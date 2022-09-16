package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("OfflineMartInfoRes")
public class OfflineMartInfoRes {
    @JsonProperty("store_id")
    int storeId;

    String name;

    int price;

    double latitude;

    double longitude;

    int distance;
}
