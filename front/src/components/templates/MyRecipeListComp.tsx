import { useRouter } from "next/router";
import React, { FC } from "react";
import { MyRecipeInfo } from "../../apis/responses/myRecipeInfo";
import { CardContainer } from "../CardContainer";
import { CarouselContainer } from "../CarouselContainer";
import { MyRecipeItem } from "../MyRecipeItem";

interface IProps {
  title?: string;
  onClickMore?: () => void;
  totalPrice?: number;
  rowSize: number;
  gridSize: number;
  myRecipeList: MyRecipeInfo[];
  onClickItem?: (id: number) => void;
}

export const MyRecipeListComp: FC<IProps> = ({
  title = "나만의 요리법",
  onClickMore,
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
  const defaultOnClickMore = () => {
    router.push("/my-recipe");
  };
  return (
    <CardContainer title={title} onClickMore={onClickMore || defaultOnClickMore} totalPrice={totalPrice}>
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
