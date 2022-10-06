import { Box, ButtonBase, styled } from "@mui/material";
import React, { FC } from "react";
import { YoutubeInfo } from "../apis/responses/youtubeInfo";
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
  padding: 10,
}));

interface IProps {
  youtubeInfo: YoutubeInfo;
}

export const YoutubeRecipeItem: FC<IProps> = ({ youtubeInfo }) => {
  const onClick = () => {
    window.open(youtubeInfo.url);
  };
  return (
    <ItemButton onClick={onClick}>
      <Box textAlign="start">
        <Image
          src={youtubeInfo.thumbnail_path}
          alt={youtubeInfo.name}
          width="720"
          height="404"
          unoptimized
        />
        <p style={{ fontSize: 14, fontWeight: "bold" }}>{youtubeInfo.name}</p>
        <p style={{ fontSize: 10, color: "#A1A1AA", marginTop: "8px" }}>
          {youtubeInfo.channel_name}
        </p>
        <p style={{ fontSize: 10, color: "#A1A1AA", marginTop: "2px" }}>
          조회수 {youtubeInfo.view}회 • {youtubeInfo.date}
        </p>
      </Box>
    </ItemButton>
  );
};
