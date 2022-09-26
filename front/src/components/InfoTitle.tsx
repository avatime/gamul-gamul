import { Box, IconButton } from "@mui/material";
import React, { FC } from "react";
import FavoriteIcon from "@mui/icons-material/Favorite";
import Image from "next/image";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";

interface IProps {
  name: string;
  bookmark: boolean;
  onClickBookmark: () => void;
  views: number;
  imagePath: string;
  basket?: boolean;
  onClickBasket?: () => void;
  isExternalImage?: boolean;
}

export const InfoTitle: FC<IProps> = ({
  name,
  bookmark,
  onClickBookmark,
  views,
  imagePath,
  basket,
  onClickBasket,
  isExternalImage = false,
}) => {
  return (
    <Box px={2}>
      <Box display="flex" flexDirection="row" alignItems="center">
        <Image
          width="32"
          height="32"
          src={imagePath}
          alt={name}
          style={{ borderRadius: 32 }}
          unoptimized={isExternalImage}
        />
        <Box p={1} />
        <p style={{ fontSize: 16, fontWeight: "bold" }}>{name}</p>
        <Box flex="1" />
        <IconButton style={{ color: bookmark ? "red" : "#A1A1AA" }} onClick={onClickBookmark}>
          <FavoriteIcon />
        </IconButton>
      </Box>
      <Box p={0.5} />
      <Box display="flex" flexDirection="row" alignItems="center">
        <p style={{ fontSize: 14, fontWeight: "bold" }}>
          오늘 {views}명의 사용자가 이 품목을 확인했어요!
        </p>
        {onClickBasket && (
          <>
            <Box flex="1" />
            <IconButton style={{ color: basket ? "red" : "#A1A1AA" }} onClick={onClickBasket}>
              <ShoppingCartIcon />
            </IconButton>
          </>
        )}
      </Box>
    </Box>
  );
};
