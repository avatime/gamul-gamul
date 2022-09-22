import { Box } from "@mui/material";
import { useRouter } from "next/router";
import React, { FC } from "react";
import { RecipeInfo } from "../../apis/responses/recipeInfo";
import { CardContainer } from "../CardContainer";
import { CarouselContainer } from "../CarouselContainer";
import { RecipeItem } from "../RecipeItem";

type Type = "row" | "column";

interface IProps {
  type?: Type;
  title?: string;
  showMore?: boolean;
  totalPrice?: number;
  rowSize?: number;
  gridSize?: number;
  recipeList: RecipeInfo[];
  onClickItem?: (id: number) => void;
}

export const RecipeListComp: FC<IProps> = ({
  type = "column",
  title = "요리법",
  showMore = false,
  totalPrice,
  rowSize = 2,
  gridSize = 3,
  recipeList,
  onClickItem,
}) => {
  const router = useRouter();
  const defaultOnClickItem = (id: number) => {
    router.push(`/recipe-info/${id}`);
  };
  return (
    <CardContainer
      title={title}
      onClickMore={showMore ? () => router.push("/recipe") : undefined}
      totalPrice={totalPrice}
    >
      {type === "column" && (
        <CarouselContainer
          itemList={recipeList}
          rowSize={rowSize}
          gridSize={gridSize}
          getItemComponent={(item) => (
            <RecipeItem
              direction={type}
              recipeInfo={item}
              onClickItem={onClickItem || defaultOnClickItem}
            />
          )}
        />
      )}
      {type === "row" && (
        <Box>
          {recipeList.map((v, i) => (
            <RecipeItem
              key={i}
              direction={type}
              recipeInfo={v}
              onClickItem={onClickItem || defaultOnClickItem}
            />
          ))}
        </Box>
      )}
    </CardContainer>
  );
};
