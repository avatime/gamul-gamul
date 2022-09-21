import { useRouter } from "next/router";
import React, { FC } from "react";
import { RecipeInfo } from "../../apis/responses/recipeInfo";
import { CardContainer } from "../CardContainer";
import { CarouselContainer } from "../CarouselContainer";
import { RecipeItem } from "../RecipeItem";

interface IProps {
  title?: string;
  onClickMore?: () => void;
  totalPrice?: number;
  rowSize: number;
  gridSize: number;
  recipeList: RecipeInfo[];
  onClickItem?: (id: number) => void;
}

export const RecipeListComp: FC<IProps> = ({
  title = "요리법",
  onClickMore,
  totalPrice,
  rowSize,
  gridSize,
  recipeList,
  onClickItem,
}) => {
  const router = useRouter();
  const defaultOnClickItem = (id: number) => {
    router.push(`/recipe-info/${id}`);
  };
  const defaultOnClickMore = () => {
    router.push("/recipe");
  };
  return (
    <CardContainer title={title} onClickMore={onClickMore || defaultOnClickMore} totalPrice={totalPrice}>
      <CarouselContainer
        itemList={recipeList}
        rowSize={rowSize}
        gridSize={gridSize}
        getItemComponent={(item) => (
          <RecipeItem
            direction="column"
            recipeInfo={item}
            onClickItem={onClickItem || defaultOnClickItem}
          />
        )}
      />
    </CardContainer>
  );
};
