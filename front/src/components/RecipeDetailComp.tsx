import { Typography } from "@mui/material";
import React, { FC } from "react";
import { CardContainer } from "./CardContainer";
import { InfoTitle } from "./InfoTitle";
import { RecipeOrderInfo } from "../../src/apis/responses/recipeOrderInfo";
import { DescriptionSharp } from "@mui/icons-material";
import Image from "next/image";

interface IProps {
  recipeOrderInfo: RecipeOrderInfo;
  order: number;
}

/**
 * @author
 * @function @RecipeDetailComp
 **/

export const RecipeDetailComp: FC<IProps> = ({ recipeOrderInfo, order }) => {
  const imagePath = recipeOrderInfo.image_path;
  const desc = recipeOrderInfo.description;

  return (
    <div>
      
      <CardContainer title={""}>
        <Typography sx={{ color: "#4411AA", fontWeight: "bold" }}>{order}.</Typography>
        <Typography>{desc}</Typography>
        <Image src={imagePath} alt="recipe_order"/>
      </CardContainer>
    </div>
  );
};
