import styled from "@emotion/styled";
import { Box, ButtonBase } from "@mui/material";
import { green } from "@mui/material/colors";
import Image from "next/image";
import React, { FC } from "react";
import { HighClass } from "../apis/responses/highClass";

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

interface IProps {
  highClass: HighClass;
  onClick: () => void;
}

export const HighClassItem: FC<IProps> = ({ highClass, onClick }) => {
  return (
    highClass && (
      <ItemButton onClick={onClick}>
        <Box
          maxHeight="163px"
          bgcolor={green[500]}
          display="flex"
          flexDirection="column"
          alignItems="center"
          marginTop="15px"
          marginBottom="15px"
          style={{
            borderRadius: "5px",
            borderColor: green[500],
            borderWidth: "2px",
            borderStyle: "solid",
          }}
        >
          <Image
            src={`/assets/ingredientsImg/${highClass?.high_class_id}.jpg`}
            alt={highClass?.name}
            width="120"
            height="120"
            style={{ borderRadius: "5px" }}
          />
          <Box paddingTop="5px" paddingBottom="5px" width="100%">
            <p style={{ color: "white" }}>{highClass?.name}</p>
          </Box>
        </Box>
      </ItemButton>
    )
  );
};
