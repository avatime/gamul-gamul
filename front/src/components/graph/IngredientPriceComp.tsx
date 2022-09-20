import styled from "@emotion/styled";
import { Box } from "@mui/material";
import React, { FC } from "react";
import { IngredientDetailInfo } from "../../apis/responses/ingredientDetailInfo";
import { CardContainer } from '../CardContainer';
import IngredientPriceGraph from './IngredientPriceGraph';

interface IProps {
  ingredientDetailInfo: IngredientDetailInfo;
  title?: string;
}

export const IngredientPriceComp: FC<IProps> = ({ ingredientDetailInfo, title = "물가 정보" }) => {
  const priceTransitionInfo = ingredientDetailInfo.price_transition_info;
  const pastPrice = priceTransitionInfo.before_price;
  const pastVol = priceTransitionInfo.pastvol;
  const todayPrice = priceTransitionInfo.price;
  const todayVol = priceTransitionInfo.todayvol;
  const quantity = ingredientDetailInfo.ingredient_info.quantity;
  const unit = ingredientDetailInfo.ingredient_info.unit;

  return (
      <Box>
        <CardContainer title={title}>
          <p style={{fontSize: 12, fontWeight: "bold"}}>어제 평균<p style={{color: !pastVol ? "black" : 0 < pastVol ? "red" : "blue"}}>{pastPrice}</p>원/{quantity}{unit}</p>
          <p style={{fontSize: 12, fontWeight: "bold"}}>오늘 평균<p style={{color: !todayVol ? "black" : 0 < todayVol ? "red" : "blue"}}>{todayPrice}</p>원/{quantity}{unit}</p>
          <IngredientPriceGraph priceTransitionInfo={priceTransitionInfo} inputWidth={370} inputHeight={300} ></IngredientPriceGraph>
        </CardContainer>
      </Box>
  );
};
