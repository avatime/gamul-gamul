import React, { FC } from "react";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { Box, IconButton, Stack, Typography } from "@mui/material";
import { useRouter } from "next/router";

interface IProps {
  text?: string;
  textColor?: string;
  backgroundColor?: string;
  end?: React.ReactNode;
}

export const BackHeader: FC<IProps> = ({ text, textColor, backgroundColor = "#f5f5f5", end }) => {
  const router = useRouter();
  return (
    <Box
      display="flex"
      flexDirection="row"
      sx={{
        width: "100vw",
        backgroundColor,
        height: "50px",
        alignItems: "center",
        position: "fixed",
        zIndex: 10,
      }}
    >
      <IconButton onClick={() => router.back()}>
        <ArrowBackIcon />
      </IconButton>
      <Typography sx={{ fontWeight: "Bold", color: textColor, flex: 1 }}>{text}</Typography>
      {end}
    </Box>
  );
};
