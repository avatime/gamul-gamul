import { Avatar, Box, ButtonBase, IconButton, styled } from "@mui/material";
import React, { FC } from "react";
import ArrowDropUpIcon from "@mui/icons-material/ArrowDropUp";
import ArrowDropDownIcon from "@mui/icons-material/ArrowDropDown";
import CloseIcon from "@mui/icons-material/Close";

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
  ingredientId: number;
  name: string;
  price: number;
  unit: string;
  quantity: number;
  volatility: number;
  onDelete?: () => void;
}

export const IngredientItem: FC<IProps> = ({
  direction,
  ingredientId,
  name,
  price,
  unit,
  quantity,
  volatility,
  onDelete,
}) => {
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
        width: direction === "row" ? "100%" : "",
      }}
      onClick={() => console.log("IngredientItem clicked!!")}
    >
      <Box
        flex="1"
        display="flex"
        alignItems="center"
        justifyContent="center"
        flexDirection={direction}
      >
        <Box position="relative" style={{ margin: direction == "column" ? 3 : 20 }}>
          <Avatar style={{ width: 60, height: 60 }} />
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

        <p style={{ fontSize: 10, fontWeight: "bold", margin: 3 }}>{name}</p>
        <Box flex={1} />
        <p style={{ fontSize: 8 }}>
          {price}원/{quantity}
          {unit}
        </p>
        <Box
          display="flex"
          alignItems="center"
          justifyContent="center"
          flexDirection="row"
          style={{
            margin: direction == "column" ? 3 : 20,
            color: 0 < volatility ? "red" : volatility < 0 ? "blue" : "",
          }}
        >
          {0 < volatility && <ArrowDropUpIcon />}
          {volatility < 0 && <ArrowDropDownIcon />}
          <p style={{ fontSize: 6, fontWeight: "bold" }}>{volatility} %</p>
        </Box>
      </Box>
    </ItemButton>
  );
};
