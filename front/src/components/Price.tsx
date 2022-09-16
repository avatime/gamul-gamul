import React, { FC } from "react";
import Image from "next/image";
import won from "../../public/assets/won.png";
import { Box, Stack, Typography } from "@mui/material";
interface IProps {
  total: number;
  Size: string;
}

/**
 * @author
 * @function @Price
 **/

export const Price: FC<IProps> = ({ total, Size }) => {
  return (
    <Stack direction="row" sx={{ alignItems: "center" }}>
      <Image src={won} alt="원" width={Size} />
      <Typography fontSize={Size} fontWeight="bold">
        총액 {total}원
      </Typography>
    </Stack>
  );
};
