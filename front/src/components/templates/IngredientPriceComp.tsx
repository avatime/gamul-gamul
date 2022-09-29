import styled from "@emotion/styled";
import { Box } from "@mui/material";
import React, { FC } from "react";
import { IngredientDetailInfo } from "../../apis/responses/ingredientDetailInfo";
import { CardContainer } from "../CardContainer";
import IngredientPriceGraph from "../IngredientPriceGraph";

interface IProps {
  ingredientDetailInfo: IngredientDetailInfo;
  title?: string;
  inputWidth: any;
  inputHeight: number;
}

export const IngredientPriceComp: FC<IProps> = ({
  ingredientDetailInfo,
  title = "물가 정보",
  inputWidth,
  inputHeight
}) => {
  const priceTransitionInfo = ingredientDetailInfo.price_transition_info;
  const pastPrice = priceTransitionInfo.before_price;
  const pastVol = priceTransitionInfo.pastvol;
  const todayPrice = priceTransitionInfo.price;
  const todayVol = priceTransitionInfo.todayvol;
  const quantity = ingredientDetailInfo.ingredient_info.quantity;
  const unit = ingredientDetailInfo.ingredient_info.unit;

  return (
      <CardContainer title={title}>
        <Box overflow={"hidden"}>
        <p style={{ fontWeight: "bold", margin: "20px 10px" }}>
          어제 평균{" "}
          <span
            style={{
              color: !pastVol ? "inherit" : 0 < pastVol ? "red" : "blue",
            }}
          >
            {pastPrice.toLocaleString()}
          </span>
          원/{quantity}
          {unit}
        </p>
        <p style={{ fontWeight: "bold", margin: "20px 10px" }}>
          오늘 평균{" "}
          <span
            style={{
              color: !todayVol ? "inherit" : 0 < todayVol ? "red" : "blue",
            }}
          >
            {todayPrice.toLocaleString()}
          </span>
          원/{quantity}
          {unit}
        </p>
        <IngredientPriceGraph
          priceTransitionInfo={priceTransitionInfo}
          inputWidth={inputWidth}
          inputHeight={inputHeight} type={"line"} />
        </Box>    
      </CardContainer>
  );
};
