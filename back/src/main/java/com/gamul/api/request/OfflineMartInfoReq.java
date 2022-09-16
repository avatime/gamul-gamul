package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("OfflineMartInfoReq")
public class OfflineMartInfoReq {
    @JsonProperty("ingredient_id")
    int ingredientId;

    double latitude;

    double longitude;

    @JsonProperty("south_west_latitude")
    double southWestLatitude;

    @JsonProperty("south_west_longitude")
    double southWestLongitude;

    @JsonProperty("north_east_latitude")
    double northEastLatitude;

    @JsonProperty("north_east_longitude")
    double northEastLongitude;
}
