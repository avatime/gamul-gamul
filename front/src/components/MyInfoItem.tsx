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
  nextPage:boolean;
  onClick:React.MouseEventHandler<HTMLLIElement>;

}

/**
 * @author
 * @function @MyInfoItem
 **/

export const MyInfoItem: FC<IProps> = ({ primary, secondary, icon, bgColor, nextPage, onClick }) => {
  return (
    <div>
      
        <ListItem onClick={onClick}>
          <ListItemAvatar >
            <Avatar sx={{backgroundColor:bgColor, opacity:"50%"}} >
              {icon}
            </Avatar>
          </ListItemAvatar>
          <ListItemText primary={primary} secondary={secondary} />
          {nextPage ? <ArrowForwardIosIcon sx={{color:"#A1A1AA"}}/>: ""}
        </ListItem>
     
    </div>
  );
};
 