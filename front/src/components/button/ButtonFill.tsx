import React, { FC } from "react";
import styles from "../../../styles/Button.module.css";
import { AnimatedButton } from "./AnimatedButton";

interface IProps {
  text: string;
  onClick: () => void;
  height: string;
  width: string;
  maxWidth: string;
  fontSize: string;
  disabled: boolean;
}

export const ButtonFill: FC<IProps> = ({
  text,
  onClick,
  height,
  width,
  maxWidth,
  fontSize,
  disabled,
}) => {
  return (
    <AnimatedButton onClick={onClick}>
      <input
        type="button"
        value={text}
        className={styles.buttonFillStyle}
        disabled={disabled}
        style={{ width, height, maxWidth, cursor: "pointer", fontSize }}
      />
    </AnimatedButton>
  );
};
