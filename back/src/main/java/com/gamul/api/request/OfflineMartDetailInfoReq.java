package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("OfflineMartDetailInfoReq")
public class OfflineMartDetailInfoReq {
    @JsonProperty("store_id")
    Long storeId;

    @JsonProperty("user_name")
    String userName;
}
