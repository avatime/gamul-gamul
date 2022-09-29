import React, { FC } from "react";
import styles from "../../../styles/Button.module.css";

interface IProps {
  text: string;
  onClick: () => void;
  height: string;
  width: string;
  maxWidth: string;
  fontSize:string;
  disabled:boolean;
}

/**
 * @author
 * @function @
 **/

export const ButtonFill: FC<IProps> = ({ text, onClick, height, width, maxWidth, fontSize, disabled }) => {
  return (
    <div>
      <input
        type="button"
        value={text}
        className={styles.buttonFillStyle}
        onClick={onClick}
        disabled={disabled}
        style={{ width, height, maxWidth, cursor: "pointer", fontSize }}
      />
    </div>
  );
};
