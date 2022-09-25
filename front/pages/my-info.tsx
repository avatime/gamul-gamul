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
import { LordIcon } from "../public/lordicon/lord-icon";

interface IProps {}
const MyInfoPage: NextPage<IProps> = (props) => {
  const router = useRouter();
  const elements = [
    {
      primary: "알러지등록",
      secondary: "알러지를 등록해보아요",
      icon: (
        <LordIcon
          src="https://cdn.lordicon.com/hdborlrw.json"
          trigger="hover"
          colors={{
            primary: "#915110",
            secondary: "#915110",
          }}
          size={30}
        />
      ),
      bgColor: "#EFE6C1",
      nextPage: true,
      path: "",
    },
    {
      primary: "나만의 요리법 등록",
      secondary: "나만의 요리법을 등록해보아요",
      icon: <LordIcon src="https://cdn.lordicon.com/rmjurjdw.json" trigger="hover" size={30} />,
      bgColor: "#B2DE9D",
      nextPage: true,
      path: "/my-recipe",
    },
    {
      primary: "찜 목록",
      secondary: "관심있는 식재료와 요리법들을 보아요",
      icon: (
        <LordIcon
          src="https://cdn.lordicon.com/rjzlnunf.json"
          trigger="hover"
          colors={{
            primary: "#121331",
            secondary: "#e83a30",
          }}
          size={30}
        />
      ),
      bgColor: "#FCDADF",
      nextPage: true,
      path: "/wish-list",
    },
    {
      primary: "가격 알림 등록",
      secondary: "관심있는 식재료의 상한가/하한가를 지정해 알림으로 받아보세요.",
      icon: (
        <LordIcon
          src="https://cdn.lordicon.com/beqdrtps.json"
          trigger="hover"
          colors={{
            primary: "#121331",
            secondary: "#08a88a",
          }}
          size={30}
        />
      ),
      bgColor: "#B6D0ED",
      nextPage: true,
      path: "/register-alarm",
    },
    {
      primary: "로그아웃",
      secondary: "",
      icon: <LordIcon src="https://cdn.lordicon.com/hirlxdux.json" trigger="hover" size={30} />,
      bgColor: "#F9BD9E",
      nextPage: false,
      path: "/logout",
    },
    {
      primary: "회원탈퇴",
      secondary: "",
      icon: (
        <LordIcon
          src="https://cdn.lordicon.com/hmtsmfsf.json"
          trigger="hover"
          colors={{
            primary: "#121331",
            secondary: "#b4b4b4",
          }}
          size={30}
        />
      ),
      nextPage: false,
      bgColor: "",
      path: "/withdraw",
    },
  ];

  const userId = getCookie("userName");

  return (
    <Box>
      <Mobile>
        <Box className={styles.PageforMobile}>
          <Stack direction="row" sx={{ alignItems: "center" }}>
            <LordIcon
              src="https://cdn.lordicon.com/dymjgskg.json"
              trigger="loop"
              colors={{
                primary: "#545454",
                secondary: "#66a1ee",
              }}
              size={70}
            />
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
              <LordIcon
                src="https://cdn.lordicon.com/dymjgskg.json"
                trigger="loop"
                colors={{
                  primary: "#545454",
                  secondary: "#66a1ee",
                }}
                size={70}
              />
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
              <LordIcon
                src="https://cdn.lordicon.com/dymjgskg.json"
                trigger="loop"
                colors={{
                  primary: "#545454",
                  secondary: "#66a1ee",
                }}
                size={70}
              />
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
    </Box>
  );
};

export default MyInfoPage;
