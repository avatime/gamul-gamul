import { useRouter } from "next/router";
import React, { FC } from "react";
import { MyRecipeInfo } from "../../apis/responses/myRecipeInfo";
import { CardContainer } from "../CardContainer";
import { CarouselContainer } from "../CarouselContainer";
import { MyRecipeItem } from "../MyRecipeItem";

interface IProps {
  title?: string;
  showMore?: boolean;
  totalPrice?: number;
  rowSize: number;
  gridSize: number;
  myRecipeList: MyRecipeInfo[];
  onClickItem?: (id: number) => void;
}

export const MyRecipeListComp: FC<IProps> = ({
  title = "나만의 요리법",
  showMore = false,
  totalPrice,
  rowSize,
  gridSize,
  myRecipeList,
  onClickItem,
}) => {
  const router = useRouter();
  const defaultOnClickItem = (id: number) => {
    router.push(`/my-recipe-info/${id}`);
  };
  return (
    <CardContainer
      title={title}
      onClickMore={showMore ? () => router.push("/my-recipe") : undefined}
      totalPrice={totalPrice}
    >
      <CarouselContainer
        itemList={myRecipeList}
        rowSize={rowSize}
        gridSize={gridSize}
        getItemComponent={(item) => (
          <MyRecipeItem
            direction="column"
            myRecipeInfo={item}
            onClickItem={onClickItem || defaultOnClickItem}
          />
        )}
      />
    </CardContainer>
  );
};
