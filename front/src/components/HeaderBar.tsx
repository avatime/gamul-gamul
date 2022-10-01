import { Badge, Box, IconButton, Stack, Typography } from "@mui/material";
import React, { FC, useState } from "react";
import styles from "../../styles/HeaderBar.module.css";
import NotificationsIcon from "@mui/icons-material/Notifications";
import SearchIcon from "@mui/icons-material/Search";
import Image from "next/image";
import grape from "../../public/assets/grape.json";
import Lottie from "lottie-react";
import { useRouter } from "next/router";
import grape2 from "../../public/assets/grape2.png";
import { Mobile } from "./Mobile";
import { Tablet } from "./Tablet";
import { Desktop } from "./Desktop";
import headerlogo from "../../public/assets/headerlogo.png";
interface IProps {
  badgeContent: number;
  onClickSearch: () => void;
}

export const HeaderBar: FC<IProps> = ({ badgeContent, onClickSearch }) => {
  const router = useRouter();
  const [play, setPlay] = useState(false);
  const startPlay = () => {
    console.log(play);

    setPlay(true);
  };
  const stopPlay = () => {
    setPlay(false);
    console.log(play);
  };
  return (
    <div>
      <Mobile>
        <Stack direction="row" className={styles.stylesforMobile}>
          <Stack
            direction="row"
            sx={{ alignItems: "center", cursor: "pointer" }}
            onMouseEnter={startPlay}
            onMouseLeave={stopPlay}
          >
            <Lottie
              animationData={grape}
              loop={true}
              autoPlay={true}
              style={{ height: "40px", width: "40px" }}
            />

            <Typography sx={{ fontWeight: "Bold" }} onClick={() => router.push("/")}>
              가물가물
            </Typography>
          </Stack>
          <Box sx={{ position: "fixed", right: 4 }}>
            <IconButton onClick={onClickSearch}>
              <SearchIcon color="success" />
            </IconButton>
            <IconButton>
              <Badge badgeContent={badgeContent} color="success">
                <NotificationsIcon />
              </Badge>
            </IconButton>
          </Box>
        </Stack>
      </Mobile>
      <Tablet>
        <Stack direction="row" className={styles.stylesforMobile}>
          <Stack
            direction="row"
            sx={{ alignItems: "center", cursor: "pointer" }}
            onMouseEnter={startPlay}
            onMouseLeave={stopPlay}
          >
            {play ? (
              <Lottie
                animationData={grape}
                loop={true}
                autoPlay={true}
                style={{ height: "40px", width: "40px" }}
              />
            ) : (
              <Image src={grape2} alt="grape2" layout="fixed" height="40px" width="40px" />
            )}

            <Typography sx={{ fontWeight: "Bold" }} onClick={() => router.push("/")}>
              가물가물
            </Typography>
          </Stack>
          <Box sx={{ position: "fixed", right: 4 }}>
            <IconButton onClick={onClickSearch}>
              <SearchIcon color="success" />
            </IconButton>
            <IconButton>
              <Badge badgeContent={badgeContent} color="success">
                <NotificationsIcon />
              </Badge>
            </IconButton>
          </Box>
        </Stack>
      </Tablet>
      <Desktop>
        <Stack direction="row" className={styles.stylesforMobile}>
          <Stack
            direction="row"
            sx={{ alignItems: "center", cursor: "pointer" }}
            onMouseEnter={startPlay}
            onMouseLeave={stopPlay}
          >
            {play ? (
              <Lottie
                animationData={grape}
                loop={true}
                autoPlay={true}
                style={{ height: "40px", width: "40px" }}
              />
            ) : (
              <Image src={grape2} alt="grape2" layout="fixed" height="40px" width="40px" />
            )}

            <Typography sx={{ fontWeight: "Bold" }} onClick={() => router.push("/")}>
              가물가물
            </Typography>
          </Stack>
          <Box sx={{ position: "fixed", right: 4 }}>
            <IconButton onClick={onClickSearch}>
              <SearchIcon color="success" />
            </IconButton>
            <IconButton>
              <Badge badgeContent={badgeContent} color="success">
                <NotificationsIcon />
              </Badge>
            </IconButton>
          </Box>
        </Stack>
      </Desktop>
    </div>
  );
};
