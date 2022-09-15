import { Box, IconButton } from "@mui/material";
import React, { FC, ReactNode } from "react";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";

interface IProps {
  title: string;
  desc?: string;
  children: ReactNode;
  style?: object;
  onClickMore?: () => void;
  totalPrice?: number;
}

export const CardContainer: FC<IProps> = ({
  title,
  desc,
  children,
  style,
  onClickMore,
  totalPrice,
}) => {
  return (
    <Box style={{ background: "white", padding: 15, borderRadius: 20, ...style }}>
      <Box display="flex" alignItems="center">
        <p style={{ fontSize: 16, fontWeight: "bold", marginLeft: 10 }}>{title}</p>
        <Box flex="1" />
        {onClickMore && (
          <IconButton onClick={onClickMore}>
            <KeyboardArrowRightIcon />
          </IconButton>
        )}
        {totalPrice && <p style={{ fontSize: 12, fontWeight: "bold", marginRight: 10 }}>{totalPrice}원</p>}
      </Box>
      {children}
    </Box>
  );
};
