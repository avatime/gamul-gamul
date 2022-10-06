package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserLoginPostReq")
public class UserLoginPostReq {
    @JsonProperty("user_name")
    @ApiModelProperty(name="유저 Name", example="your_Id")
    String userName;

    @ApiModelProperty(name="유저 Password", example="your_password")
    String password;

}
