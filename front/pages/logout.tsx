import { Box, Stack, Typography } from "@mui/material";
import { NextPage } from "next";
import { useRouter } from "next/router";
import React, { FC } from "react";
import { ButtonFill } from "../src/components/button/ButtonFill";
import { removeCookie } from "../src/utils/cookie";
import { Page } from "../src/components/Page";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";

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
      <Mobile>
        <Box className={styles.PageforMobile}>
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
        </Box>
      </Mobile>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <Box
            sx={{
              position: "absolute",
              width: "60vw",
              left: 150,
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
        </Box>
      </Tablet>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <Box
            sx={{
              position: "absolute",
              width: "60vw",
              left: 200,
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
        </Box>
      </Desktop>
    </Page>
  );
};
export default LogoutPage;
