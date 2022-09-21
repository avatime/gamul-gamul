import { Button, Typography } from "@mui/material";
import { Box } from "@mui/system";
import React, { FC, MouseEventHandler } from "react";
import styles from "../../../styles/Button.module.css";
import { Desktop } from "../Desktop";
import { Mobile } from "../Mobile";
import { Tablet } from "../Tablet";
interface IProps {
  text: string;
  icon: React.ReactNode;
  onClick: MouseEventHandler<HTMLButtonElement>;
  width: string;
  height: string;

}

/**
* @author

**/

export const ButtonOutlined: FC<IProps> = ({ text, icon, onClick, width, height}) => {
  const boxStyle = {
    display: "flex",
    alignItems: "center",
  };

  return (
    <div>
      <Button color="success" endIcon={icon} onClick={onClick} sx={{width:`${width}`, height:`${height}`}}>
        <Typography sx={{fontWeight:"bold", color:"#000"}}>{text}</Typography>
      </Button>
    </div>
  );
};
