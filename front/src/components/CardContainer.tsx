import { Box, IconButton } from "@mui/material";
import React, { FC, ReactNode } from "react";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";

interface IProps {
  title: string;
  children: ReactNode;
  style?: object;
  onClickMore?: () => void;
  totalPrice?: number;
}

export const CardContainer: FC<IProps> = ({ title, children, style, onClickMore, totalPrice }) => {
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
        <p style={{ fontSize: 16, fontWeight: "bold", marginLeft: 10 }}>{title}</p>
        <Box flex="1" />
        {onClickMore && (
          <IconButton onClick={onClickMore}>
            <KeyboardArrowRightIcon />
          </IconButton>
        )}
        {totalPrice && (
          <p style={{ fontSize: 12, fontWeight: "bold", marginRight: 10 }}>{totalPrice}원</p>
        )}
      </Box>
      <Box maxHeight="inherit" overflow="auto">
        {children}
      </Box>
    </Box>
  );
};
