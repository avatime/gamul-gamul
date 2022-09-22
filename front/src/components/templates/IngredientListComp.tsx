import { Box, Grid } from "@mui/material";
import { useRouter } from "next/router";
import React, { FC } from "react";
import { IngredientInfo } from "../../apis/responses/ingredientInfo";
import { CardContainer } from "../CardContainer";
import { CarouselContainer } from "../CarouselContainer";
import { IngredientItem } from "../IngredientItem";

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
  ingredientList: IngredientInfo[];
  onClickItem?: (id: number) => void;
  style?: object;
}

export const IngredientListComp: FC<IProps> = ({
  scrollDirection = "horizon",
  direction = "column",
  title = "식재료",
  showMore = false,
  totalPrice,
  rowSize = 2,
  gridSize = 3,
  ingredientList,
  onClickItem,
  style,
}) => {
  const router = useRouter();
  const defaultOnClickItem = (id: number) => {
    router.push(`/ingredient-info/${id}`);
  };
  return (
    <CardContainer
      title={title}
      onClickMore={showMore ? () => router.push("/ingredient") : undefined}
      totalPrice={totalPrice}
      style={style}
    >
      {scrollDirection === "horizon" && (
        <CarouselContainer
          itemList={ingredientList}
          rowSize={rowSize}
          gridSize={gridSize}
          getItemComponent={(item) => (
            <IngredientItem
              direction={direction}
              ingredientInfo={item}
              onClickItem={onClickItem || defaultOnClickItem}
            />
          )}
        />
      )}
      {scrollDirection === "vertical" &&
        (direction === "column" ? (
          <Grid container>
            {ingredientList.map((v, i) => (
              <Grid item key={i} margin="auto">
                <IngredientItem
                  direction={direction}
                  ingredientInfo={v}
                  onClickItem={onClickItem || defaultOnClickItem}
                />
              </Grid>
            ))}
          </Grid>
        ) : (
          <Box>
            {ingredientList.map((v, i) => (
              <IngredientItem
                key={i}
                direction={direction}
                ingredientInfo={v}
                onClickItem={onClickItem || defaultOnClickItem}
              />
            ))}
          </Box>
        ))}
    </CardContainer>
  );
};
