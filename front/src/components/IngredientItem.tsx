import { Avatar, Box, IconButton } from "@mui/material";
import React, { FC } from "react";
import ArrowDropUpIcon from "@mui/icons-material/ArrowDropUp";
import ArrowDropDownIcon from "@mui/icons-material/ArrowDropDown";
import CloseIcon from "@mui/icons-material/Close";
import { IngredientInfo } from "../apis/responses/ingredientInfo";
import { AnimatedButton } from "./button/AnimatedButton";

type Direction = "row" | "column";

interface IProps {
  direction: Direction;
  ingredientInfo: IngredientInfo | null;
  onDelete?: () => void;
  onClickItem: (id: number) => void;
}

export const IngredientItem: FC<IProps> = ({
  direction,
  ingredientInfo,
  onDelete,
  onClickItem,
}) => {
  const onMouseDownDelete = (e: any) => {
    e.stopPropagation();
  };
  const onClickDelete = (e: any) => {
    onDelete?.();
    e.stopPropagation();
  };
  const avatarSize = direction === "row" ? 42 : 60;
  return (
    <AnimatedButton
      style={{
        borderRadius: 10,
        padding: direction == "column" ? 15 : 0,
        width: direction === "row" ? "100%" : "auto",
        visibility: ingredientInfo ? "visible" : "hidden",
        cursor: "pointer",
      }}
      onClick={() => onClickItem(ingredientInfo?.ingredient_id || 0)}
    >
      <Box
        flex="1"
        display="flex"
        alignItems="center"
        justifyContent="center"
        flexDirection={direction}
      >
        <Box position="relative" style={{ margin: direction == "column" ? 3 : 12 }}>
          <Avatar style={{ width: avatarSize, height: avatarSize }} />
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

        <p style={{ fontSize: 10, fontWeight: "bold", margin: 3 }}>
          {ingredientInfo?.name || "이름"}
        </p>
        <Box flex={1} />
        <p style={{ fontSize: 8 }}>
          {ingredientInfo?.price || 0}원/{ingredientInfo?.quantity}
          {ingredientInfo?.unit}
        </p>
        <Box
          display="flex"
          alignItems="center"
          justifyContent="center"
          flexDirection="row"
          style={{
            margin: direction == "column" ? 3 : 20,
            color: !ingredientInfo?.volatility
              ? "inherit"
              : 0 < ingredientInfo.volatility
              ? "red"
              : "blue",
          }}
        >
          {ingredientInfo?.volatility && 0 < ingredientInfo.volatility ? (
            <ArrowDropUpIcon />
          ) : (
            <ArrowDropDownIcon />
          )}
          <p style={{ fontSize: 6, fontWeight: "bold" }}>{ingredientInfo?.volatility || 0} %</p>
        </Box>
      </Box>
    </AnimatedButton>
  );
};
