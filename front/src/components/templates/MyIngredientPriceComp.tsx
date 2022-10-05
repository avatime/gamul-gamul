import styled from "@emotion/styled";
import { Box } from "@mui/material";
import React, { FC, useState, useEffect } from "react";
import { IngredientDetailInfo } from "../../apis/responses/ingredientDetailInfo";
import { CardContainer } from "../CardContainer";
import IngredientPriceGraph from "../IngredientPriceGraph";
import { IngredientInfo } from '../../apis/responses/ingredientInfo';
import { PriceTransitionInfo } from '../../apis/responses/priceTransitionInfo';

interface IProps {
  title?: string;
  inputWidth: any;
  inputHeight: number;
  blackList?: number[];
  ingredientList: IngredientInfo[];
  priceTransitionInfo: PriceTransitionInfo;
}

export const MyIngredientPriceComp: FC<IProps> = ({
  title = "물가 정보",
  inputWidth,
  inputHeight,
  blackList,
  ingredientList,
  priceTransitionInfo,
}) => {
  const pastPrice = priceTransitionInfo.before_price;
  const pastVol = priceTransitionInfo.pastvol;
  const todayPrice = priceTransitionInfo.price;
  const todayVol = priceTransitionInfo.todayvol;
  const [graph, setGraph] = useState(true);

  useEffect(() => {
    if(!!blackList) {
      ingredientList.forEach((v) => {
        if(blackList.includes(v.ingredient_id)) {
          setGraph(false);
        }
      })
    }
  }, []);
  return (
      <CardContainer title={title}>
        <Box overflow={"hidden"}>
        <p style={{ fontWeight: "bold", margin: "20px 10px" }}>
          어제 평균{" "}
          <span
            style={{
              color: pastPrice == 0 ? "#808080" : 0 < pastVol ? "red" : 0 > pastVol ? "blue" : "black",
            }}
          >
            {pastPrice != 0 ? pastPrice.toLocaleString() : "정보 없음"}
          </span>
          {!!pastPrice && (`원`)}
        </p>
        <p style={{ fontWeight: "bold", margin: "20px 10px" }}>
          오늘 평균{" "}
          <span
            style={{
              color: todayPrice == 0 ? "#808080" : 0 < todayVol ? "red" : 0 > todayVol ? "blue" : "black",
            }}
          >
            {todayPrice != 0 ? todayPrice.toLocaleString() : "정보 없음"}
          </span>
          {!!todayPrice && (`원`)}
        </p>
        {graph && (<IngredientPriceGraph
          priceTransitionInfo={priceTransitionInfo}
          inputWidth={inputWidth}
          inputHeight={inputHeight} type={"line"} myRecipe />)}
        </Box>
      </CardContainer>
  );
};
