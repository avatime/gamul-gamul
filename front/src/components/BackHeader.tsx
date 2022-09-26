import React, { FC } from "react";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { IconButton, Stack, Typography } from "@mui/material";
import { useRouter } from "next/router";

interface IProps {
  text?: string;
  textColor?: string;
  backgroundColor?: string;
}

export const BackHeader: FC<IProps> = ({ text, textColor, backgroundColor = "#f5f5f5"}) => {
  const router = useRouter();
  return (
    <Stack
      direction="row"
      sx={{
        width: "100vw",
        backgroundColor,
        height: "50px",
        alignItems: "center",
        position: "fixed",
        zIndex:10,
      }}
    >
      <IconButton onClick={() => router.back()}>
        <ArrowBackIcon />
      </IconButton>
      <Typography sx={{ fontWeight: "Bold", color: textColor }}>{text}</Typography>
    </Stack>
  );
};
