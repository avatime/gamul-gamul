import {
  BottomNavigation,
  BottomNavigationAction,
  Box,
  List,
  ListItem,
  ListItemText,
  Menu,
  Paper,
  Typography,
} from "@mui/material";
import React, { FC, useState } from "react";
import { Desktop } from "./Desktop";
import { Mobile } from "./Mobile";
import { Tablet } from "./Tablet";
import HomeIcon from "@mui/icons-material/Home";
import EggIcon from "@mui/icons-material/Egg";
import RestaurantIcon from "@mui/icons-material/Restaurant";
import PersonIcon from "@mui/icons-material/Person";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import styles from "../../styles/Navbar.module.css";
import { Stack } from "@mui/system";

interface IProps {
  activeIndex: number;
}

export const Navbar: FC<IProps> = ({ activeIndex }) => {
  return (
    <div>
      <Mobile>
        <BottomNavigation
          showLabels
          value={activeIndex}
          sx={{
            "& .BottomNavigation": {
              color: "#A1A1AA",
            },
            "& .Mui-selected, .Mui-selected > svg": {
              color: "#000",
            },
            position: "fixed",
            bottom: 0,
            left: 0,
            right: 0,
            borderTop: "2px #F5F5F4 solid",
            borderTopRightRadius: 20,
            borderTopLeftRadius: 20,
          }}
        >
          <BottomNavigationAction label="홈" icon={<HomeIcon />} />
          <BottomNavigationAction label="식재료" icon={<EggIcon />} />
          <BottomNavigationAction label="바구니" icon={<RestaurantIcon />} />
          <BottomNavigationAction label="요리법" icon={<ShoppingCartIcon />} />
          <BottomNavigationAction label="내정보" icon={<PersonIcon />} />
        </BottomNavigation>
      </Mobile>
      <Desktop>
        <Box
          sx={{
            width: 250,
            height: "100vh",
            backgroundColor: "#f5f5f4",
            position: "fixed",
            left: 0,
            top: 50,
            bottom: 0,
          }}
        >
          <Stack direction="row">
            <HomeIcon />
            <Typography>홈</Typography>
          </Stack>
        </Box>
      </Desktop>
      <Tablet>
        <Box
          sx={{
            width: 150,
            height: "100vh",
            backgroundColor: "#f5f5f4",
            position: "fixed",
            left: 0,
            top: 50,
            bottom: 0,
          }}
        >
          <HomeIcon />
          <Typography>홈</Typography>
        </Box>
      </Tablet>
    </div>
  );
};
