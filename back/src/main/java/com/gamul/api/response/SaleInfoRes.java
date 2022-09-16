package com.gamul.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@ApiModel("SaleInfoRes")
public class SaleInfoRes {
    ArrayList<PriceInfoRes> daily;

    ArrayList<PriceInfoRes> monthly;

    ArrayList<PriceInfoRes> yearly;

}
