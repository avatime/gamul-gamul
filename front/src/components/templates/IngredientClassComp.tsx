import React, { FC } from "react";
import { HighClass } from "../../apis/responses/highClass";
import { IngredientInfo } from "../../apis/responses/ingredientInfo";

interface IProps {
  highClassList: HighClass[];
  ingredientList: IngredientInfo[];
}

export const IngredientClassComp: FC<IProps> = ({ highClassList, ingredientList }) => {
  return <div></div>;
};
