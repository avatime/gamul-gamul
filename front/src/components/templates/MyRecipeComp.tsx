import { useRouter } from "next/router";
import React, { FC } from "react";
import { MyRecipeInfo } from "../../apis/responses/myRecipeInfo";
import { CardContainer } from "../CardContainer";
import { MyRecipeTextItem } from "../MyRecipeTextItem";

interface IProps {
  title?: string;
  myRecipeList: MyRecipeInfo[];
}

export const MyRecipeComp: FC<IProps> = ({
  title = "나만의 요리법",
  myRecipeList,
}) => {
    const router = useRouter();
    const movePage = () => {
        router.push(`/register-my-recipe`);
    };

  return (
    <CardContainer title={title} onClickMore={movePage} addPlus={true}>
      {myRecipeList.map((data, index) => {
        return <MyRecipeTextItem key={index} myRecipeInfo={data} />;
      })}
    </CardContainer>
  );
};
