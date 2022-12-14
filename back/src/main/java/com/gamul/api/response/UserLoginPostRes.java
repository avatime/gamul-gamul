package com.gamul.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gamul.common.model.response.BaseResponseBody;
import com.gamul.common.util.Token;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserLoginPostRes")
public class UserLoginPostRes extends BaseResponseBody {
    @JsonProperty("access_token")
    @ApiModelProperty(name="JWT 인증 토큰", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
    String accessToken;

    @JsonProperty("refresh_token")
    @ApiModelProperty(name="JWT 인증 토큰", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
    String refreshToken;

    public static UserLoginPostRes of(Integer statusCode, String message, Token token) {
        UserLoginPostRes res = new UserLoginPostRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        if(token != null){
            res.setAccessToken(token.getAccessToken());
            res.setRefreshToken(token.getRefreshToken());
        }
        return res;
    }
}
