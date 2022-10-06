import styled from "@emotion/styled";
import { Box, ButtonBase, Grid } from "@mui/material";
import React, { FC } from "react";
import { IngredientInfo } from "../apis/responses/ingredientInfo";
import { OfflineMartInfo } from "../apis/responses/offlineMartInfo";

interface IProps {
  offlineMartInfo?: OfflineMartInfo;
  ingredientInfo?: IngredientInfo;
}

export const OfflineMartInfoItem: FC<IProps> = ({ offlineMartInfo, ingredientInfo }) => {
  return (
    <Grid container mt={1}>
      <Grid item xs={8}>
        <p style={{ fontSize: 12, fontWeight: "bold" }}>{offlineMartInfo?.name || "마트 이름"}</p>
      </Grid>
      <Grid item xs={3}>
        {offlineMartInfo ? (
          <p style={{ fontSize: 12 }}>{offlineMartInfo?.price.toLocaleString() || 0}원</p>
        ) : (
          <p style={{ fontSize: 12, fontWeight: "bold" }}>
            가격({ingredientInfo?.quantity}
            {ingredientInfo?.unit})
          </p>
        )}
      </Grid>
      <Grid item xs={1}>
        {offlineMartInfo ? (
          <p style={{ fontSize: 12 }}>{offlineMartInfo?.distance.toLocaleString() || "거리"}m</p>
        ) : (
          <p style={{ fontSize: 12, fontWeight: "bold" }}>거리</p>
        )}
      </Grid>
    </Grid>
  );
};
