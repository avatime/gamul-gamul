import { useRouter } from "next/router";
import React, { FC } from "react";
import { IngredientInfo } from "../../apis/responses/ingredientInfo";
import { CardContainer } from "../CardContainer";
import { CarouselContainer } from "../CarouselContainer";
import { IngredientItem } from "../IngredientItem";

interface IProps {
  title?: string;
  showMore?: boolean;
  totalPrice?: number;
  rowSize: number;
  gridSize: number;
  ingredientList: IngredientInfo[];
  onClickItem?: (id: number) => void;
}

export const IngredientListComp: FC<IProps> = ({
  title = "식재료",
  showMore = false,
  totalPrice,
  rowSize,
  gridSize,
  ingredientList,
  onClickItem,
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
      <CarouselContainer
        itemList={ingredientList}
        rowSize={rowSize}
        gridSize={gridSize}
        getItemComponent={(item) => (
          <IngredientItem
            direction="column"
            ingredientInfo={item}
            onClickItem={onClickItem || defaultOnClickItem}
          />
        )}
      />
    </CardContainer>
  );
};
