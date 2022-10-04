package com.gamul.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiModel("SaleInfoRes")
public class SaleInfoRes {
    List<PriceInfoRes> daily;

    List<PriceInfoRes> monthly;

    List<PriceInfoRes> yearly;

}
