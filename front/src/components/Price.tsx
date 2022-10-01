import React, { FC } from "react";
import Image from "next/image";
import won from "../../public/assets/won.png";
import { Box, Stack, Typography } from "@mui/material";
import Lottie from "lottie-react";
import coin from "../../public/assets/coin.json";
interface IProps {
  total: number;
  size: string;
}

/**
 * @author
 * @function @Price
 **/

export const Price: FC<IProps> = ({ total, size }) => {
  return (
    <Stack direction="row" sx={{  }}>
      <Lottie animationData={coin} style={{ width:size}} />
      <Box p={0.5} />
      <Typography fontSize={size} fontWeight="bold">
        총액 {total.toLocaleString()}원
      </Typography>
    </Stack>
  );
};
