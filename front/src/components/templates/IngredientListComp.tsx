import { Box } from "@mui/material";
import { useRouter } from "next/router";
import React, { FC } from "react";
import { IngredientInfo } from "../../apis/responses/ingredientInfo";
import { CardContainer } from "../CardContainer";
import { CarouselContainer } from "../CarouselContainer";
import { IngredientItem } from "../IngredientItem";

type Type = "row" | "column";

interface IProps {
  type?: Type;
  title?: string;
  showMore?: boolean;
  totalPrice?: number;
  rowSize?: number;
  gridSize?: number;
  ingredientList: IngredientInfo[];
  onClickItem?: (id: number) => void;
  itemTitle?: (ingredientInfo: IngredientInfo) => string;
}

export const IngredientListComp: FC<IProps> = ({
  type = "column",
  title = "식재료",
  showMore = false,
  totalPrice,
  rowSize = 2,
  gridSize = 3,
  ingredientList,
  onClickItem,
  itemTitle,
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
    >
      {type === "column" && (
        <CarouselContainer
          itemList={ingredientList}
          rowSize={rowSize}
          gridSize={gridSize}
          getItemComponent={(item) => (
            <IngredientItem
              direction={type}
              ingredientInfo={item}
              onClickItem={onClickItem || defaultOnClickItem}
              title={itemTitle?.(item)}
            />
          )}
        />
      )}
      {type === "row" && (
        <Box overflow="visible">
          {ingredientList.map((v, i) => (
            <IngredientItem
              key={i}
              direction={type}
              ingredientInfo={v}
              onClickItem={onClickItem || defaultOnClickItem}
              title={itemTitle?.(v)}
            />
          ))}
        </Box>
      )}
    </CardContainer>
  );
};
