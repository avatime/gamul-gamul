import {
  Avatar,
  Box,
  List,
  ListItem,
  ListItemAvatar,
  ListItemText,
  Stack,
  Typography,
} from "@mui/material";
import { NextPage } from "next";
import { Mobile } from "../src/components/Mobile";
import { MyInfoItem } from "../src/components/MyInfoItem";
import { Navbar } from "../src/components/Navbar";
import styles from "../styles/Page.module.css";
import { getCookie } from "../src/utils/cookie";
import { useRouter } from "next/router";
import { Desktop } from "../src/components/Desktop";
import { Tablet } from "../src/components/Tablet";
import Lottie, { useLottie } from "lottie-react";
import loveheart from "../public/assets/loveheart.json";
import exit from "../public/assets/exit.json";
import withdraw from "../public/assets/withdraw.json";
import cook from "../public/assets/cook.json";
import allergy from "../public/assets/allergy.json";
import shark from "../public/assets/shark.json";
import { Page } from "../src/components/Page";
import React, { useState } from "react";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import allergy3 from "../public/assets/allergy2.gif";
import allergy2 from "../public/assets/allergy3.png";
import cook2 from "../public/assets/cook2.png"
import loveheart2 from "../public/assets/loveheart2.png"
import chart2 from "../public/assets/chart2.png"
import exit2 from "../public/assets/exit2.png"
import chart from "../public/assets/chart.json"
import withdraw2 from "../public/assets/withdraw2.png"
import Image from "next/image";
interface IProps {}
const MyInfoPage: NextPage<IProps> = (props) => {
  const router = useRouter();

  const [playAllergy, setPlayAllergy] = useState(false);
  const [playCook, setPlayCook] = useState(false);
  const [playLoveheart, setPlayLoveheart] = useState(false);
  const [playExit, setPlayExit] = useState(false);
  const [playWithdraw, setPlayWithdraw] = useState(false);
  const [playChart, setPlayChart] = useState(false);

  const startAllergyPlay = () => {
    setPlayAllergy(true);
  };

  const stopAllergyPlay = () => {
    setPlayAllergy(false);
  };

  const startCookPlay = () => {
    setPlayCook(true);
  };

  const stopCookPlay = () => {
    setPlayCook(false);
  };

  const startLoveheartPlay = () => {
   setPlayLoveheart(true);
  };

  const stopLoveheartPlay = () => {
    setPlayLoveheart(false)
  };

  const startExitPlay = () => {
    setPlayExit(true);
  };

  const stopExitPlay = () => {
    setPlayExit(false);
  };

  const startWithdrawPlay = () => {
    setPlayWithdraw(true);
  };

  const stopWithdrawPlay = () => {
    setPlayWithdraw(false);
  };

  const startChartPlay = () => {
    setPlayChart(true);
  };

  const stopChartPlay = () => {
    setPlayChart(false);
  };

  const userId = getCookie("userName");

  return (
    <Page>
      <Mobile>
        <Box className={styles.PageforMobile} sx={{ backgroundColor: "#fff" }}>
          <Stack direction="row" sx={{ alignItems: "center", marginLeft:1, paddingTop:1 }}>
            <Typography sx={{ fontSize: "large", fontWeight: "bold" }}>
              {userId}님, 안녕하세요
            </Typography>
          </Stack>
          <List>
              <ListItem
                onClick={() => router.push("/register-allergy")}
                onMouseEnter={startAllergyPlay}
                onMouseLeave={stopAllergyPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4"}}>
                    {playAllergy ? (
                      <Lottie animationData={allergy} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={allergy2} alt="allergy" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="알러지 등록" secondary="내 알러지를 등록해보아요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>

              <ListItem
                onClick={() => router.push("/my-recipe")}
                onMouseEnter={startCookPlay}
                onMouseLeave={stopCookPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4" }}>
                  {playCook ? (
                      <Lottie animationData={cook} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={cook2} alt="cook" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="나만의 요리법" secondary="나만의 요리법을 등록해보아요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>

              <ListItem
                onClick={() => router.push("/wish-list")}
                onMouseEnter={startLoveheartPlay}
                onMouseLeave={stopLoveheartPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4"}}>
                  {playLoveheart ? (
                      <Lottie animationData={loveheart} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={loveheart2} alt="loveheart" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="찜 목록" secondary="관심있는 식재료와 요리법들을 보아요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>


              <ListItem
                onClick={() => router.push("/register-alarm")}
                onMouseEnter={startChartPlay}
                onMouseLeave={stopChartPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4"}}>
                  {playChart ? (
                      <Lottie animationData={chart} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={chart2} alt="chart" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="가격 알림 등록" secondary="관심있는 식재료의 상한가/하한가를 지정해 알림으로 받아보세요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>

             

              <ListItem
                onClick={() => router.push("/logout")}
                onMouseEnter={startExitPlay}
                onMouseLeave={stopExitPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4" }}>
                  {playExit ? (
                      <Lottie animationData={exit} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={exit2} alt="exit" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="로그아웃" secondary="" />
              </ListItem>

              <ListItem
                onClick={() => router.push("/withdraw")}
                onMouseEnter={startWithdrawPlay}
                onMouseLeave={stopWithdrawPlay}
                sx={{ cursor: "pointer" }}
              >
                
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4" }}>
                  {playWithdraw ? (
                      <Lottie animationData={withdraw} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={withdraw2} alt="withdraw" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="회원탈퇴" secondary="" />
              </ListItem>
            </List>
        </Box>
      </Mobile>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <Box
            sx={{
              position: "absolute",
              top:70,
              left: 200,
              right: 0,
              margin: "auto",
              maxWidth: "500px",
              width: "60vw",
              backgroundColor:"#fff",
            }}
          >
            <Stack direction="row" sx={{ alignItems: "center", marginLeft:1, paddingTop:1}}>
              <Typography sx={{ fontSize: "large", fontWeight: "bold" }}>
                {userId}님, 안녕하세요
              </Typography>
            </Stack>
            <List>
              <ListItem
                onClick={() => router.push("/register-allergy")}
                onMouseEnter={startAllergyPlay}
                onMouseLeave={stopAllergyPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4"}}>
                    {playAllergy ? (
                      <Lottie animationData={allergy} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={allergy2} alt="allergy" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="알러지 등록" secondary="내 알러지를 등록해보아요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>

              <ListItem
                onClick={() => router.push("/my-recipe")}
                onMouseEnter={startCookPlay}
                onMouseLeave={stopCookPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4" }}>
                  {playCook ? (
                      <Lottie animationData={cook} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={cook2} alt="cook" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="나만의 요리법" secondary="나만의 요리법을 등록해보아요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>

              <ListItem
                onClick={() => router.push("/wish-list")}
                onMouseEnter={startLoveheartPlay}
                onMouseLeave={stopLoveheartPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4"}}>
                  {playLoveheart ? (
                      <Lottie animationData={loveheart} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={loveheart2} alt="loveheart" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="찜 목록" secondary="관심있는 식재료와 요리법들을 보아요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>


              <ListItem
                onClick={() => router.push("/register-alarm")}
                onMouseEnter={startChartPlay}
                onMouseLeave={stopChartPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4"}}>
                  {playChart ? (
                      <Lottie animationData={chart} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={chart2} alt="chart" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="가격 알림 등록" secondary="관심있는 식재료의 상한가/하한가를 지정해 알림으로 받아보세요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>

             

              <ListItem
                onClick={() => router.push("/logout")}
                onMouseEnter={startExitPlay}
                onMouseLeave={stopExitPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4" }}>
                  {playExit ? (
                      <Lottie animationData={exit} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={exit2} alt="exit" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="로그아웃" secondary="" />
              </ListItem>

              <ListItem
                onClick={() => router.push("/withdraw")}
                onMouseEnter={startWithdrawPlay}
                onMouseLeave={stopWithdrawPlay}
                sx={{ cursor: "pointer" }}
              >
                
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4" }}>
                  {playWithdraw ? (
                      <Lottie animationData={withdraw} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={withdraw2} alt="withdraw" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="회원탈퇴" secondary="" />
              </ListItem>
            </List>
          </Box>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
        <Box
            sx={{
              position: "absolute",
              top:70,
            
              left: 150,
              right: 0,
              margin: "auto",
              maxWidth: "500px",
              width: "60vw",
              backgroundColor:"#fff",
        
            }}
          >
            <Stack direction="row" sx={{ alignItems: "center" , marginLeft:1, paddingTop:1 }}>
              <Typography sx={{ fontSize: "large", fontWeight: "bold" }}>
                {userId}님, 안녕하세요
              </Typography>
            </Stack>
            <List>
              <ListItem
                onClick={() => router.push("/register-allergy")}
                onMouseEnter={startAllergyPlay}
                onMouseLeave={stopAllergyPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4"}}>
                    {playAllergy ? (
                      <Lottie animationData={allergy} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={allergy2} alt="allergy" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="알러지 등록" secondary="내 알러지를 등록해보아요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>

              <ListItem
                onClick={() => router.push("/my-recipe")}
                onMouseEnter={startCookPlay}
                onMouseLeave={stopCookPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4" }}>
                  {playCook ? (
                      <Lottie animationData={cook} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={cook2} alt="cook" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="나만의 요리법" secondary="나만의 요리법을 등록해보아요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>

              <ListItem
                onClick={() => router.push("/wish-list")}
                onMouseEnter={startLoveheartPlay}
                onMouseLeave={stopLoveheartPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4"}}>
                  {playLoveheart ? (
                      <Lottie animationData={loveheart} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={loveheart2} alt="loveheart" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="찜 목록" secondary="관심있는 식재료와 요리법들을 보아요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>


              <ListItem
                onClick={() => router.push("/register-alarm")}
                onMouseEnter={startChartPlay}
                onMouseLeave={stopChartPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4"}}>
                  {playChart ? (
                      <Lottie animationData={chart} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={chart2} alt="chart" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="가격 알림 등록" secondary="관심있는 식재료의 상한가/하한가를 지정해 알림으로 받아보세요." />
                <ArrowForwardIosIcon sx={{ color: "#A1A1AA" }} />
              </ListItem>

             

              <ListItem
                onClick={() => router.push("/logout")}
                onMouseEnter={startExitPlay}
                onMouseLeave={stopExitPlay}
                sx={{ cursor: "pointer" }}
              >
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4" }}>
                  {playExit ? (
                      <Lottie animationData={exit} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={exit2} alt="exit" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="로그아웃" secondary="" />
              </ListItem>

              <ListItem
                onClick={() => router.push("/withdraw")}
                onMouseEnter={startWithdrawPlay}
                onMouseLeave={stopWithdrawPlay}
                sx={{ cursor: "pointer" }}
              >
                
                <ListItemAvatar>
                  <Avatar sx={{ backgroundColor: "#f5f5f4" }}>
                  {playWithdraw ? (
                      <Lottie animationData={withdraw} loop={true} autoPlay={true} />
                    ) : (
                      <Image src={withdraw2} alt="withdraw" />
                    )}
                  </Avatar>
                </ListItemAvatar>
                <ListItemText primary="회원탈퇴" secondary="" />
              </ListItem>
            </List>
          </Box>
        </Box>
      </Tablet>
    </Page>
  );
};

export default MyInfoPage;
