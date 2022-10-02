import React, { FC, useEffect, useState } from "react";
import { Box } from "@mui/system";
import { Grid, Avatar, IconButton } from '@mui/material';
import { IngredientInfo } from "../../apis/responses/ingredientInfo";
import IngredientPriceGraph from "../IngredientPriceGraph";
import { PriceTransitionInfo } from "../../apis/responses/priceTransitionInfo";
import { useRouter } from "next/router";

interface IProps {
  ingredientInfo: IngredientInfo;
  priceTransitionInfo: PriceTransitionInfo;
}

export const IngredientBarGraphComp: FC<IProps> = ({
  ingredientInfo,
  priceTransitionInfo,
}) => {
  const router = useRouter();

  const movePage = () => {
    router.push(`/ingredient-info/${ingredientInfo.ingredient_id}`);
  }

  return (
    <Grid container>
      <Grid
        item
        xs={2}
        sx={{
          display: "flex",
          flexDirection: "column",
          justifyContent: "center",
        }}
      >
        <Box
          sx={{
            display: "flex",
            justifyContent: "flex-end",
            marginBottom: "5px",
          }}
        >
          <IconButton onClick={movePage}>
            <Avatar
            src={`/assets/ingredientsImg/${ingredientInfo?.ingredient_id}.jpg`}
            alt={ingredientInfo.name}
            sx={{ width: "50px", height: "50px" }}
          /></IconButton>
          
        </Box>
        <p
          style={{ fontSize: 16, display: "flex", justifyContent: "flex-end" }}
        >
          {ingredientInfo.name}
        </p>
        <p
          style={{
            fontSize: 22,
            fontWeight: "bold",
            display: "flex",
            justifyContent: "flex-end",
          }}
        >
          {ingredientInfo.price.toLocaleString()}
        </p>
        <p
          style={{
            fontSize: 14,
            color: "#808080",
            display: "flex",
            justifyContent: "flex-end",
          }}
        >
          원/{ingredientInfo.quantity}
          {ingredientInfo.unit}
        </p>
      </Grid>
      <Grid item xs={10}>
        <IngredientPriceGraph
          priceTransitionInfo={priceTransitionInfo}
          inputWidth="95%"
          inputHeight={240}
          type="bar"
        />
      </Grid>
    </Grid>
  );
};
