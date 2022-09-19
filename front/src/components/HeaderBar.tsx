import { Badge, Box, IconButton, Stack, Typography } from "@mui/material";
import React, { FC } from "react";
import styles from "../../styles/HeaderBar.module.css";
import { Desktop } from "./Desktop";
import { Mobile } from "./Mobile";
import { Tablet } from "./Tablet";
import NotificationsIcon from "@mui/icons-material/Notifications";
import SearchIcon from "@mui/icons-material/Search";
import { SearchBar } from "./SearchBar";

interface IProps {
  badgeContent: number;
}

/**
 * author
 * @function @
 **/

export const HeaderBar: FC<IProps> = ({ badgeContent }) => {
  return (
    <div>
      <Mobile>
        <Stack direction="row" className={styles.stylesforMobile}>
          <Typography sx={{ fontWeight: "Bold" }}>가물가물</Typography>
          <Box sx={{ position: "fixed", right: 4 }}>
            <IconButton>
              <SearchIcon color="success" />
            </IconButton>
            <IconButton>
              <Badge badgeContent={badgeContent} color="success">
                <NotificationsIcon />
              </Badge>
            </IconButton>
          </Box>
        </Stack>
      </Mobile>
      <Desktop>
        <Stack direction="row" className={styles.styles}>
          <Typography sx={{ fontWeight: "Bold" }}>가물가물</Typography>
          <Box sx={{ position: "fixed", left: 250 }}>
            <SearchBar color="#f5f5f4" />
          </Box>
          <Box sx={{ position: "fixed", right: 4 }}>
            <IconButton>
              <Badge badgeContent={badgeContent} color="success">
                <NotificationsIcon />
              </Badge>
            </IconButton>
          </Box>
        </Stack>
      </Desktop>
      <Tablet>
        <Stack direction="row" className={styles.styles}>
          <Typography sx={{ fontWeight: "Bold" }}>가물가물</Typography>
          <Box sx={{ position: "fixed", left: 150 }}>
            <SearchBar color="#f5f5f4" />
          </Box>
          <Box sx={{ position: "fixed", right: 4 }}>
            <IconButton>
              <Badge badgeContent={badgeContent} color="success">
                <NotificationsIcon />
              </Badge>
            </IconButton>
          </Box>
        </Stack>
      </Tablet>
    </div>
  );
};
