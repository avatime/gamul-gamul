import { Badge, Box, IconButton, Stack, Typography } from "@mui/material";
import React, { FC } from "react";
import styles from "../../styles/HeaderBar.module.css";
import NotificationsIcon from "@mui/icons-material/Notifications";
import SearchIcon from "@mui/icons-material/Search";

interface IProps {
  badgeContent: number;
  onClickSearch: () => void;
}

export const HeaderBar: FC<IProps> = ({ badgeContent, onClickSearch }) => {
  return (
    <Stack direction="row" className={styles.stylesforMobile}>
    <Typography sx={{ fontWeight: "Bold" }}>가물가물</Typography>
    <Box sx={{ position: "fixed", right: 4 }}>
      <IconButton onClick={onClickSearch}>
        <SearchIcon color="success" />
      </IconButton>
      <IconButton>
        <Badge badgeContent={badgeContent} color="success">
          <NotificationsIcon />
        </Badge>
      </IconButton>
    </Box>
  </Stack>
  );
};
