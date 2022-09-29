import { Box, Stack, Typography } from "@mui/material";
import { NextPage } from "next";
import { Mobile } from "../src/components/Mobile";
import { MyInfoItem } from "../src/components/MyInfoItem";
import { Navbar } from "../src/components/Navbar";
import styles from "../styles/Page.module.css";
import { getCookie } from "../src/utils/cookie";
import { useRouter } from "next/router";
import { Desktop } from "../src/components/Desktop";
import { Tablet } from "../src/components/Tablet";
import Lottie from "lottie-react";
import loveheart from "../public/assets/loveheart.json";
import exit from "../public/assets/exit.json";
import withdraw from "../public/assets/withdraw.json";
import chart from "../public/assets/chart.json";
import cook from "../public/assets/cook.json";
import allergy from "../public/assets/allergy.json";
import shark from "../public/assets/shark.json";
import { Page } from '../src/components/Page';

interface IProps {}
const MyInfoPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const elements = [
    {
      primary: "알러지 목록",
      secondary: "내 알러지를 등록해보아요",
      icon: ( 
        <> <Lottie animationData={allergy} loop={true} autoPlay={false}/></>
      ),
      bgColor: "#f5f5f4",
      nextPage: true,
      path: "/register-allergy",
    },
    {
      primary: "나만의 요리법",
      secondary: "나만의 요리법을 등록해보아요",
      icon: (
        <>
          <Lottie animationData={cook} loop={true} autoPlay={false}/>
        </>
      ),
      bgColor: "#f5f5f4",
      nextPage: true,
      path: "/my-recipe",
    },
    {
      primary: "찜 목록",
      secondary: "관심있는 식재료와 요리법들을 보아요",
      icon: (
        <Lottie animationData={loveheart} loop={true} autoPlay={false}/>
      ),
      bgColor: "#f5f5f4",
      nextPage: true,
      path: "/wish-list",
    },
    {
      primary: "가격 알림 등록",
      secondary: "관심있는 식재료의 상한가/하한가를 지정해 알림으로 받아보세요.",
      icon: (
        <>
       <Lottie animationData={chart} loop={true} autoPlay={false}/>
        </>
      ),
      bgColor: "#f5f5f4",
      nextPage: true,
      path: "/register-alarm",
    },
    {
      primary: "로그아웃",
      secondary: "",
      icon: (
       <> <Lottie animationData={exit} loop={true} autoPlay={false}/></>
      ),
      bgColor: "#f5f5f4",
      nextPage: false,
      path: "/logout",
    },
    {
      primary: "회원탈퇴",
      secondary: "",
      icon: (
       <> <Lottie animationData={withdraw} loop={true} autoPlay={false}/></>
      ),
      nextPage: false,
      bgColor: "#f5f5f4",
      path: "/withdraw",
    },
  ];

  const userId = getCookie("userName");

  return (
    <Page>
      <Mobile>
        <Box className={styles.PageforMobile} sx={{backgroundColor:"#fff"}}>
          <Stack direction="row" sx={{ alignItems: "center"}}>
            <Typography sx={{ fontSize: "large", fontWeight: "bold" }}>
             
              {userId}님, 안녕하세요
            </Typography>
          </Stack>
          {elements?.map((item, idx) => {
            return (
              <Box key={idx}>
                <MyInfoItem
                  primary={item.primary}
                  secondary={item.secondary}
                  icon={item.icon}
                  bgColor={item.bgColor}
                  nextPage={item.nextPage}
                  onClick={() => router.push(item.path)}
                />
              </Box>
            );
          })}
        </Box>
      </Mobile>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <Box
            sx={{
              position: "absolute",
              left: 0,
              right: 0,
              margin: "auto",
              maxWidth: "500px",
              width: "60vw",
            }}
          >
            <Stack direction="row" sx={{ alignItems: "center" }}>
            
              <Typography sx={{ fontSize: "large", fontWeight: "bold" }}>
                {userId}님, 안녕하세요
              </Typography>
            </Stack>
            {elements?.map((item, idx) => {
              return (
                <Box key={idx}>
                  <MyInfoItem
                    primary={item.primary}
                    secondary={item.secondary}
                    icon={item.icon}
                    bgColor={item.bgColor}
                    nextPage={item.nextPage}
                    onClick={() => router.push(item.path)}
                  />
                </Box>
              );
            })}
            <Navbar activeIndex={4} />
          </Box>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <Box
            sx={{
              position: "absolute",
              left: 0,
              right: 0,
              margin: "auto",
              maxWidth: "500px",
              width: "60vw",
            }}
          >
            <Stack
              direction="row"
              sx={{
                alignItems: "center",
              }}
            >
            
              <Typography sx={{ fontSize: "large", fontWeight: "bold" }}>
                {userId}님, 안녕하세요
              </Typography>
            </Stack>
            {elements?.map((item, idx) => {
              return (
                <Box key={idx}>
                  <MyInfoItem
                    primary={item.primary}
                    secondary={item.secondary}
                    icon={item.icon}
                    bgColor={item.bgColor}
                    nextPage={item.nextPage}
                    onClick={() => router.push(item.path)}
                  />
                </Box>
              );
            })}
          </Box>
        </Box>
      </Tablet>
    </Page>
  );
};

export default MyInfoPage;
