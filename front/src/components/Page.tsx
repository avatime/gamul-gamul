import { Box } from "@mui/material";
import { AnimatePresence, motion } from "framer-motion";
import React, { FC } from "react";

interface IProps {
  children: React.ReactNode;
}

export const Page: FC<IProps> = ({ children }) => {
  return (
    <Box overflow="hidden">
      <AnimatePresence exitBeforeEnter>
        <motion.div
          initial={{ y: 20, opacity: 0 }}
          animate={{ y: 0, opacity: 1 }}
          exit={{ y: -20, opacity: 0 }}
          transition={{ duration: 0.6 }}
          style={{ overflow: "hidden" }}
        >
          {children}
        </motion.div>
      </AnimatePresence>
    </Box>
  );
};
