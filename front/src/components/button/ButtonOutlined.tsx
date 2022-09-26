import { Button } from "@mui/material";
import React, { FC } from "react";

interface IProps {
  text: string;
  icon: React.ReactNode;
  onClick: () => void;
  style?: object;
}

export const ButtonOutlined: FC<IProps> = ({ text, icon, onClick, style }) => {
  return (
    <Button
      color="success"
      endIcon={icon}
      style={{
        backgroundColor: "white",
        borderRadius: "10px",
        height: "50px",
        ...style,
      }}
      onClick={onClick}
    >
      <p style={{ color: "black", fontSize: 16, fontWeight: "bold" }}>{text}</p>
    </Button>
  );
};
