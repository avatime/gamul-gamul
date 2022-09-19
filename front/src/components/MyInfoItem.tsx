import React, { FC } from "react";
import { Desktop } from "./Desktop";
import { Mobile } from "./Mobile";
import { Tablet } from "./Tablet";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";
import ListItemAvatar from "@mui/material/ListItemAvatar";
import Avatar from "@mui/material/Avatar";
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';

interface IProps {
  primary: string;
  secondary: string;
  icon: React.ReactNode;
  bgColor: string;
}

/**
 * @author
 * @function @MyInfoItem
 **/

export const MyInfoItem: FC<IProps> = ({ primary, secondary, icon, bgColor }) => {
  return (
    <div>
      <Mobile>
        <ListItem>
          <ListItemAvatar >
            <Avatar sx={{backgroundColor:bgColor, opacity:"40%"}} >
              {icon}
            </Avatar>
          </ListItemAvatar>
          <ListItemText primary={primary} secondary={secondary} />
          <ArrowForwardIosIcon sx={{color:"#A1A1AA"}}/>
        </ListItem>
      </Mobile>
      <Desktop>f</Desktop>
      <Tablet>f</Tablet>
    </div>
  );
};
