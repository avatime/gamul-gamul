import React, { FC } from "react";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { Box, IconButton, Stack, Typography } from "@mui/material";
import { Mobile } from "./Mobile";
import { useRouter } from "next/router";

interface IProps {
    text:string;
    textColor:string;
}

/**
 * @author
 * @function @
 **/

export const BackHeader: FC<IProps> = ({text ,textColor}) => {
  const router = useRouter();
  return (
    <div>
        <Stack direction="row" sx={{ width: "100vw", backgroundColor: "none", height: "50px", alignItems:"center"}}>
          <IconButton onClick={() => router.back()}>
            <ArrowBackIcon />
          </IconButton>
          <Typography sx={{fontWeight:"Bold", color:textColor}}>{text}</Typography>
        </Stack>
    </div>
  );
};
