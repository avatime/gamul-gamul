package com.gamul.api.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("PriceInfoRes")
public class PriceInfoRes {
    String date;

    int price;

    String unit;

    int quantity;

    public PriceInfoRes(String date, int price){
        this.date = date;
        this.price = price;
    }
}
