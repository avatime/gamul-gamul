import { BottomNavigation, Box, Typography } from "@mui/material";
import React, { FC, useState } from "react";
import { Desktop } from "./Desktop";
import { Mobile } from "./Mobile";
import { Tablet } from "./Tablet";
import HomeIcon from "@mui/icons-material/Home";
import EggIcon from "@mui/icons-material/Egg";
import RestaurantIcon from "@mui/icons-material/Restaurant";
import PersonIcon from "@mui/icons-material/Person";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { styled } from "@mui/material/styles";
import { Stack } from "@mui/system";
import MuiBottomNavigationAction from "@mui/material/BottomNavigationAction";
import { useRouter } from 'next/router'

interface IProps {
  activeIndex: number;
}

const BottomNavigationAction = styled(MuiBottomNavigationAction)(`
  color: #A1A1AA;

  &.Mui-selected, .Mui-selected > svg {
    color: #000;
  }
`);

const elements = [
  {
    text: "홈",
    icon: (isActive: boolean) => <HomeIcon sx={{ color: isActive ? "#fff" : "#A1A1AA" }} />,
    path:''
  },
  {
    text: "식재료",
    icon: (isActive: boolean) => <EggIcon sx={{ color: isActive ? "#fff" : "#A1A1AA" }} />,
    path:'/ingredient',
  },
  {
    text: "바구니",
    icon: (isActive: boolean) => <ShoppingCartIcon sx={{ color: isActive ? "#fff" : "#A1A1AA" }} />,
    path:'/basket',
  },
  {
    text: "요리법",
    icon: (isActive: boolean) => <RestaurantIcon sx={{ color: isActive ? "#fff" : "#A1A1AA" }} />,
    path:'/recipe',
  },
  {
    text: "내정보",
    icon: (isActive: boolean) => <PersonIcon sx={{ color: isActive ? "#fff" : "#A1A1AA" }} />,
    path:'/my-info',
  },
];

const elementActiveStyles = {
  borderRadius: 15,
  backgroundColor: "#4411AA",
  alignItems: "center",
  color: "#fff !important",
};

export const Navbar: FC<IProps> = ({ activeIndex }) => {
  const router = useRouter()

  return (
    <div>
      <Mobile>
        <Box>
          <BottomNavigation
            showLabels
            value={activeIndex}
            sx={{
              position: "fixed",
              bottom: 0,
              left: 0,
              right: 0,
              borderTop: "2px #F5F5F4 solid",
              borderTopRightRadius: 20,
              borderTopLeftRadius: 20,
              zIndex: 5,
            }}
          >
            <BottomNavigationAction label="홈" icon={<HomeIcon />}   onClick={() => router.push('')} />
            <BottomNavigationAction label="식재료" icon={<EggIcon />}  onClick={() => router.push('/ingredient')}/>
            <BottomNavigationAction label="바구니" icon={< ShoppingCartIcon/>}  onClick={() => router.push('/basket')} />
            <BottomNavigationAction label="요리법" icon={<RestaurantIcon />}  onClick={() => router.push('/recipe')}/>
            <BottomNavigationAction label="내정보" icon={<PersonIcon />}  onClick={() => router.push('/my-info')}/>
          </BottomNavigation>
        </Box>
      </Mobile>
      <Desktop>
        <Box
          sx={{
            width: 250,
            height: "100vh",
            backgroundColor: "#fff",
            position: "fixed",
            left: 0,
            top: 50,
            bottom: 0,
          }}
        >
          <Box p={0.5} />
          {elements.map((item, idx) => {
            return (
              <Stack
                key={idx}
                direction="row"  
                onClick={() => router.push(item.path)}
                sx={{
                  marginLeft: 1,
                  padding: 2,
                  ...(idx === activeIndex ? elementActiveStyles : {}),
                }}
              >
                {item.icon(idx === activeIndex)}
                <Typography
                  sx={{
                    marginLeft: 1,
                    marginRight: 1,
                    color: idx === activeIndex ? "#fff" : "#A1A1AA",
                    fontWeight: "bold",
                  }}
                >
                  {item.text}
                </Typography>
              </Stack>
            );
          })}
        </Box>
      </Desktop>
      <Tablet>
        <Box
          sx={{
            width: 150,
            height: "100vh",
            backgroundColor: "#fff",
            position: "fixed",
            left: 0,
            top: 50,
            bottom: 0,
          }}
        >
          <Box p={0.5} />
          {elements.map((item, idx) => {
            return (
              <Stack
                key={idx}
                direction="row"
                onClick={() => router.push(item.path)}
                sx={{
                  marginLeft: 1,
                  padding: 2,
                  ...(idx === activeIndex ? elementActiveStyles : {}),
                }}
              >
                {item.icon(idx === activeIndex)}
                <Typography
                  sx={{
                    marginLeft: 1,
                    marginRight: 1,
                    color: idx === activeIndex ? "#fff" : "#A1A1AA",
                    fontWeight: "bold",
                  }}
                >
                  {item.text}
                </Typography>
              </Stack>
            );
          })}
        </Box>
      </Tablet>
    </div>
  );
};
