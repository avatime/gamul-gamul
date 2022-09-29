import React, { FC } from "react";
import Image from "next/image";
import won from "../../public/assets/won.png";
import { Box, Stack, Typography } from "@mui/material";
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
    <Stack direction="row" sx={{ alignItems: "center" }}>
      <Image src={won} alt="원" width={size} height={size} />
      <Box p={0.5}/>
      <Typography fontSize={size} fontWeight="bold">
        총액 {total.toLocaleString()}원
      </Typography>
    </Stack>
  );
};
