import styled from "@emotion/styled";
import { Box, ButtonBase } from "@mui/material";
import React, { FC } from "react";
import { IngredientInfo } from "../apis/responses/ingredientInfo";
import { OfflineMartInfo } from '../apis/responses/offlineMartInfo';

interface IProps {
  offlineMartInfo?: OfflineMartInfo;
  ingredientInfo?: IngredientInfo;
}

export const OfflineMartInfoItem: FC<IProps> = ({ offlineMartInfo, ingredientInfo }) => {
  return (
      <Box
        flex="1"
        display="flex"
        alignItems="center"
        justifyContent="center"
        flexDirection="row"
      >
        <Box position="relative" >
          <p style={{fontSize: 12, fontWeight: "bold"}}>{offlineMartInfo?.name || "마트 이름"}</p>
        </Box>
        <Box flex={2} />
        {offlineMartInfo ? <p style={{ fontSize: 12, margin: 3 }}>
          {offlineMartInfo?.price || 0}원
        </p>
        : <p style={{ fontSize: 12, fontWeight: "bold"}}>가격({ingredientInfo?.quantity}
        {ingredientInfo?.unit})</p>}
        <Box flex={1} />
        {offlineMartInfo ? <p style={{ fontSize: 10 }}>{offlineMartInfo?.distance || "거리"}m</p> : <p style={{ fontSize: 12, fontWeight: "bold"}}>거리</p>}
      </Box>
  );
};
