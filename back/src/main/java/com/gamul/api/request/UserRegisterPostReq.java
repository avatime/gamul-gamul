package com.gamul.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ApiModel("UserRegisterPostReq")
@ToString
public class UserRegisterPostReq extends BaseResponseBody {
    @JsonProperty("user_name")
    @ApiModelProperty(name="유저 NAME", example="your_Id")
    String userName;

    @ApiModelProperty(name="유저 Password", example="your_password")
    String password;

    public static UserRegisterPostReq of (Integer statusCode, String message, UserRegisterPostReq registerInfo){
        UserRegisterPostReq res = new UserRegisterPostReq();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUserName(registerInfo.getUserName());
        res.setPassword(registerInfo.getPassword());
        return res;
    }

}
