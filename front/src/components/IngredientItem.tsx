import { Avatar, Box, ButtonBase, IconButton, styled } from "@mui/material";
import React, { FC } from "react";
import ArrowDropUpIcon from "@mui/icons-material/ArrowDropUp";
import ArrowDropDownIcon from "@mui/icons-material/ArrowDropDown";
import CloseIcon from "@mui/icons-material/Close";
import { IngredientInfo } from "../apis/responses/ingredientInfo";
import Image from "next/image";

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

type Direction = "row" | "column";

interface IProps {
  direction: Direction;
  ingredientInfo: IngredientInfo;
  onDelete?: () => void;
  onClickItem: (id: number) => void;
  tail?: React.ReactNode;
  noVol?: boolean;
  title?: string;
}

export const IngredientItem: FC<IProps> = ({
  direction,
  ingredientInfo,
  onDelete,
  onClickItem,
  tail,
  noVol,
  title,
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
    <ItemButton
      style={{
        borderRadius: 10,
        padding: direction == "column" ? 15 : 0,
        width: direction === "row" ? "100%" : "auto",
        visibility: ingredientInfo ? "visible" : "hidden",
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
        <Box
          position="relative"
          style={{ margin: direction == "column" ? 3 : 12 }}
        >
          <Image
            width={avatarSize}
            height={avatarSize}
            alt={ingredientInfo?.name}
            src={`/assets/ingredientsImg/${ingredientInfo?.ingredient_id}.jpg`}
            style={{ borderRadius: avatarSize }}
          />
          {onDelete && (
            <IconButton
              style={{ position: "absolute", right: -20, top: -15 }}
              color="inherit"
              onMouseDown={onMouseDownDelete}
              onClick={onClickDelete}
            >
              <CloseIcon style={{ width: 16, height: 16 }} />
            </IconButton>
          )}
        </Box>

        <p style={{ fontSize: 12, fontWeight: "bold", margin: 3 }}>
          {title || ingredientInfo?.name || "??????"}
        </p>
        <Box flex={1} />
        <p style={{ fontSize: 10 }}>
          {ingredientInfo?.price.toLocaleString() || 0}???/
          {ingredientInfo?.quantity}
          {ingredientInfo?.unit}
        </p>
        {!!!noVol && (<Box
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
          <p style={{ fontSize: 8, fontWeight: "bold" }}>
            {ingredientInfo?.volatility || 0} %
          </p>
        </Box>)}
        <Box
          style={{
            margin: direction == "column" ? 3 : 10,
          }}
        >
          {tail}
        </Box>
      </Box>
    </ItemButton>
  );
};
