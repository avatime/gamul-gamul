import { Box, IconButton } from "@mui/material";
import React, { FC, ReactNode } from "react";
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";
import AddIcon from "@mui/icons-material/Add";
import { Price } from "./Price";

interface IProps {
  title: string;
  children: ReactNode;
  style?: object;
  onClickMore?: () => void;
  totalPrice?: number;
  addPlus?: boolean;
  onClickBack?: () => void | undefined;
}

export const CardContainer: FC<IProps> = ({
  title,
  children,
  style,
  onClickMore,
  totalPrice,
  addPlus,
  onClickBack,
}) => {
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
      <Box display="flex" alignItems="center" height="40px">
        <p
          style={{
            fontSize: 16,
            fontWeight: "bold",
            marginLeft: 10,
            marginBottom: 5,
            marginTop: 5,
          }}
        >
          {title}
        </p>
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
        {onClickBack && !addPlus && (
          <IconButton onClick={onClickBack}>
            <KeyboardArrowLeftIcon />
          </IconButton>
        )}
        {!!totalPrice && <Price total={totalPrice} size="12px" />}
      </Box>
      <Box maxHeight="inherit">{children}</Box>
    </Box>
  );
};
