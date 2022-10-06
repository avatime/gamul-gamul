import React, { FC } from "react";
import { Box, IconButton, ListItem } from "@mui/material";
import { MyRecipeInfo } from "../apis/responses/myRecipeInfo";
import { useRouter } from "next/router";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";

interface IProps {
  myRecipeInfo?: MyRecipeInfo;
}

export const MyRecipeTextItem: FC<IProps> = ({ myRecipeInfo }) => {
  const router = useRouter();
  const onMove = () => {
    const id = myRecipeInfo?.my_recipe_id;
    router.push(`/my-recipe-info/${id}`);
  };
  const onClickMove = (e: any) => {
    onMove();
    e.stopPropagation();
  };

  return (
    <Box
      display="flex"
      alignItems="center"
      justifyContent="center"
      flexDirection="row"
      onClick={onClickMove}
      style={{
        cursor: "pointer",
      }}
    >
      <span style={{ fontSize: 12, fontWeight: "bold", marginLeft: "10px" }}>
        {myRecipeInfo?.name || "요리법 이름"}
      </span>
      <Box flex="1" />
      <IconButton color="inherit">
        <KeyboardArrowRightIcon />
      </IconButton>
    </Box>
  );
};
