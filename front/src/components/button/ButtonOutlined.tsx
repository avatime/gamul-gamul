import { Box } from "@mui/system";
import React, { FC } from "react";
import styles from "../../../styles/Button.module.css";
import { Desktop } from "../Desktop";
import { Mobile } from "../Mobile";
import { Tablet } from "../Tablet";
interface IProps {
  text: string;
  icon: React.ReactNode;
}

/**
* @author

**/

export const ButtonOutlined: FC<IProps> = ({ text, icon }) => {
  const boxStyle = {
    display: "flex",
    alignItems: "center",
  };

  return (
    <div>
      <Mobile>
        <button className={styles.buttonOutlinedStyleMobile}>
          <Box sx={boxStyle}>
            {text}
            {icon}
          </Box>
        </button>
      </Mobile>
      <Desktop>
        <button className={styles.buttonOutlinedStyle}>
          <Box sx={boxStyle}>
            {text}
            {icon}
          </Box>
        </button>
      </Desktop>
      <Tablet>
        <button className={styles.buttonOutlinedStyle}>
          <Box sx={boxStyle}>
            {text}
            {icon}
          </Box>
        </button>
      </Tablet>
    </div>
  );
};
