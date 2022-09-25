import React, { FC, MouseEventHandler, useState } from "react";
import { NextPage } from "next";
import styles from "../styles/Page.module.css";
import { Mobile } from "../src/components/Mobile";
import { Box, Stack, Typography } from "@mui/material";
import { ButtonFill } from "../src/components/button/ButtonFill";
import { Router, useRouter } from "next/router";
import { ApiClient } from "../src/apis/apiClient";
import { getCookie, removeCookie } from "../src/utils/cookie";
import { Desktop } from "../src/components/Desktop";
import { Tablet } from "../src/components/Tablet";

interface IProps {}

/**
 * @author
 * @function @widthdraw
 **/

const WithdrawPage: NextPage<IProps> = (props) => {
  const [next, setNext] = useState(false);
  const userId = getCookie("userName");

  const router = useRouter();
  const apiClient = ApiClient.getInstance();
  const withdraw = () => {
    setNext(true);
    apiClient.withdrawal(userId);
    removeCookie("userName");
    removeCookie("token");
  };
  return (
    <div>
      <Box>
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
            {!next ? (
              <>
                <Typography sx={{ fontWeight: "bold", fontSize: "large" }}>
                  가물가물 서비스를 떠나시겠습니까?
                </Typography>
                <Box p={4} />
                <ButtonFill
                  text={"네"}
                  onClick={withdraw}
                  height={"50px"}
                  width={"60vw"}
                  maxWidth={"500px"}
                  fontSize={""}
                  disabled={false}
                />
              </>
            ) : (
              <>
                <Typography sx={{ fontWeight: "bold", fontSize: "large" }}>
                  탈퇴되었습니다.
                </Typography>
                <Box p={4} />
                <ButtonFill
                  text={"메인으로 돌아가기"}
                  onClick={() => router.push("/")}
                  height={"50px"}
                  width={"60vw"}
                  maxWidth={"500px"}
                  fontSize={""}
                  disabled={false}
                />

            
              </>
            )}
          </Stack>
        </Box>
      </Box>
    </div>
  );
};

export default WithdrawPage;
