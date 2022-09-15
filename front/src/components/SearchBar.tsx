import { Box, IconButton, InputBase, Paper, Stack } from "@mui/material";
import React, { FC } from "react";
import { Desktop } from "./Desktop";
import { Mobile } from "./Mobile";
import { Tablet } from "./Tablet";
import SearchIcon from "@mui/icons-material/Search";
import styles from "../../styles/SearchBar.module.css";

/* white : #fff
   연한grey : #f5f5f4 */

interface IProps {
  color: string;
}

export const SearchBar: FC<IProps> = ({ color }) => {
  return (
    <div>
      <Mobile>
        <Box sx={{ marginLeft: "5%", marginRight: "5%", borderRadius: 20 }}>
          <Stack
            direction="row"
            sx={{ borderRadius: 20, bgcolor: `${color}` }}
            className={styles.stylesforMobile}
          >
            <InputBase sx={{ paddingLeft: 2 }} />
            <Box sx={{ marginLeft: "50%" }}>
              <IconButton color="success">
                <SearchIcon />
              </IconButton>
            </Box>
          </Stack>
        </Box>
      </Mobile>
      <Desktop>
        <Stack
          direction="row"
          sx={{ borderRadius: 20, bgcolor: `${color}`, display: "flex", paddingLeft: 3 }}
          className={styles.styles}
        >
          <InputBase sx={{ flex: 1 }} />
          <Box>
            <IconButton color="success">
              <SearchIcon />
            </IconButton>
          </Box>
        </Stack>
      </Desktop>
      <Tablet>
        <Stack
          direction="row"
          sx={{ borderRadius: 20, bgcolor: `${color}`, display: "flex", paddingLeft: 3}}
          className={styles.styles}
        >
          <InputBase sx={{ flex: 1}} />
          <Box>
            <IconButton color="success">
              <SearchIcon />
            </IconButton>
          </Box>
        </Stack>
      </Tablet>
    </div>
  );
};
