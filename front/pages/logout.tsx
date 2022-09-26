import { Box, Stack, Typography } from "@mui/material";
import { NextPage } from "next";
import { useRouter } from "next/router";
import React, { FC } from "react";
import { ButtonFill } from "../src/components/button/ButtonFill";
import { removeCookie } from "../src/utils/cookie";
import { Page } from '../src/components/Page';

interface IProps {}

const LogoutPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const logout = () => {
    removeCookie("userName");
    removeCookie("token");
    router.push("/");
  };
  return (
    <Page>
      <Box
        sx={{
          position: "absolute",
          width: "60vw",
          left: 0,
          right: 0,
          top: "30%",
          maxWidth: "500px",
          margin: "auto",
        }}
      >
        <Stack direction="column" sx={{ alignItems: "center" }}>
          <>
            <Typography sx={{ fontWeight: "bold", fontSize: "large" }}>
              로그아웃 하시겠습니까?
            </Typography>
            <Box p={2} />
            <ButtonFill
              text={"네"}
              onClick={logout}
              height={"50px"}
              width={"60vw"}
              maxWidth={"500px"}
              fontSize={""}
              disabled={false}
            />
          </>
        </Stack>
      </Box>
    </Page>
  );
};
export default LogoutPage;
