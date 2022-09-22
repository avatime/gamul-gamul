import { Box } from "@mui/material";
import { useRouter } from "next/router";
import React, { FC } from "react";
import { RecipeInfo } from "../../apis/responses/recipeInfo";
import { CardContainer } from "../CardContainer";
import { CarouselContainer } from "../CarouselContainer";
import { RecipeItem } from "../RecipeItem";

type ScrollDirection = "horizon" | "vertical";
type Direction = "row" | "column";

interface IProps {
  scrollDirection?: ScrollDirection;
  direction?: Direction;
  title?: string;
  showMore?: boolean;
  totalPrice?: number;
  rowSize?: number;
  gridSize?: number;
  recipeList: RecipeInfo[];
  onClickItem?: (id: number) => void;
  style?: object;
}

export const RecipeListComp: FC<IProps> = ({
  scrollDirection = "row",
  direction = "column",
  title = "요리법",
  showMore = false,
  totalPrice,
  rowSize = 2,
  gridSize = 3,
  recipeList,
  onClickItem,
  style,
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
      style={style}
    >
      {scrollDirection === "horizon" && (
        <CarouselContainer
          itemList={recipeList}
          rowSize={rowSize}
          gridSize={gridSize}
          getItemComponent={(item) => (
            <RecipeItem
              direction={direction}
              recipeInfo={item}
              onClickItem={onClickItem || defaultOnClickItem}
            />
          )}
        />
      )}
      {scrollDirection === "vertical" && (
        <Box>
          {recipeList.map((v, i) => (
            <RecipeItem
              key={i}
              direction={direction}
              recipeInfo={v}
              onClickItem={onClickItem || defaultOnClickItem}
            />
          ))}
        </Box>
      )}
    </CardContainer>
  );
};
