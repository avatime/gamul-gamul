package com.gamul.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SearchPostReq")
public class SearchPostReq {
    String keyword;
}
