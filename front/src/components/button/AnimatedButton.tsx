import { motion } from "framer-motion";
import React, { FC } from "react";

interface IProps {
  whileHover?: object;
  whileTap?: object;
  children: React.ReactNode;
  style?: object;
  onClick: () => void;
}

export const AnimatedButton: FC<IProps> = ({
  whileHover = { scale: [null, 1.2, 1.0, 1.1] },
  whileTap = { scale: [null, 0.9, 1.0, 0.8] },
  children,
  style,
  onClick,
}) => {
  return (
    <motion.div
      whileHover={whileHover}
      whileTap={whileTap}
      transition={{ duration: 0.3 }}
      style={style}
      onClick={onClick}
    >
      {children}
    </motion.div>
  );
};
