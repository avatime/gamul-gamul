import { Avatar, Box, ButtonBase, IconButton, styled } from "@mui/material";
import React, { FC } from "react";
import CloseIcon from "@mui/icons-material/Close";
import { MyRecipeInfo } from "../apis/responses/myRecipeInfo";

const ItemButton = styled(ButtonBase)(() => ({
  "&:hover, &.Mui-focusVisible": {
    zIndex: 1,
    "& .MuiImageBackdrop-root": {
      opacity: 0.15,
    },
    "& .MuiImageMarked-root": {
      opacity: 0,
    },
  },
}));

interface IProps {}

type Direction = "row" | "column";

interface IProps {
  direction: Direction;
  myRecipeInfo: MyRecipeInfo;
  onDelete?: () => void;
  onClickItem: (id: number) => void;
}

export const MyRecipeItem: FC<IProps> = ({ direction, myRecipeInfo, onDelete, onClickItem }) => {
  const onMouseDownDelete = (e: any) => {
    e.stopPropagation();
  };
  const onClickDelete = (e: any) => {
    onDelete?.();
    e.stopPropagation();
  };
  return (
    <ItemButton
      style={{
        borderRadius: 10,
        padding: direction == "column" ? 20 : 0,
        width: direction === "row" ? "100%" : "auto",
        visibility: myRecipeInfo ? "visible" : "hidden",
      }}
      onClick={() => onClickItem(myRecipeInfo?.my_recipe_id || 0)}
    >
      <Box
        flex="1"
        display="flex"
        alignItems="center"
        justifyContent="center"
        flexDirection={direction}
      >
        <Box position="relative" style={{ margin: direction == "column" ? 3 : 20 }}>
          <Avatar style={{ width: 60, height: 60 }} src={myRecipeInfo?.image_path}/>
          {onDelete && (
            <IconButton
              style={{ position: "absolute", right: -20, top: -15 }}
              color="inherit"
              onMouseDown={onMouseDownDelete}
              onClick={onClickDelete}
            >
              <CloseIcon />
            </IconButton>
          )}
        </Box>

        <p style={{ fontSize: 10, fontWeight: "bold", margin: 3 }}>{myRecipeInfo?.name || "이름"}</p>
        <Box flex={1} />
        {direction === "row" && <></>}
      </Box>
    </ItemButton>
  );
};