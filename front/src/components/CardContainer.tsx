import { Box, IconButton } from "@mui/material";
import React, { FC, ReactNode } from "react";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";
import AddIcon from '@mui/icons-material/Add';
import { Price } from "./Price";

interface IProps {
  title: string;
  children: ReactNode;
  style?: object;
  onClickMore?: () => void;
  totalPrice?: number;
  addPlus?: boolean;
}

export const CardContainer: FC<IProps> = ({ title, children, style, onClickMore, totalPrice, addPlus }) => {
  return (
    <Box
      display="flex"
      flexDirection="column"
      bgcolor="white"
      padding="15px"
      margin="15px"
      borderRadius="20px"
      style={style}
    >
      <Box display="flex" alignItems="center">
        <p style={{ fontSize: 16, fontWeight: "bold", marginLeft: 10, marginBottom: 10 }}>{title}</p>
        <Box flex="1" />
        {onClickMore && !addPlus && (
          <IconButton onClick={onClickMore}>
            <KeyboardArrowRightIcon />
          </IconButton>
        )}
        {onClickMore && addPlus && (
          <IconButton onClick={onClickMore}>
            <AddIcon />
          </IconButton>
        )}
        {totalPrice && (
          <Price total={totalPrice} size="12px" />
        )}
      </Box>
      <Box maxHeight="inherit">
        {children}
      </Box>
    </Box>
  );
};
