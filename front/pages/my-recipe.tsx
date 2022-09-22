import { Box } from "@mui/material";
import { NextPage } from "next";
import { Desktop } from "../src/components/Desktop";
import { HeaderBar } from "../src/components/HeaderBar";
import { Mobile } from "../src/components/Mobile";
import { Navbar } from "../src/components/Navbar";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";

interface IProps {}

const MyRecipePage: NextPage<IProps> = (props) => {
  return (
    <Box className="page-background">
      <Desktop>
        <HeaderBar badgeContent={6} />
        <Box className={styles.PageforDesktop}>
        </Box>
        <Navbar activeIndex={4} />
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <HeaderBar badgeContent={6} />
          <Navbar activeIndex={4} />
        </Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
          <HeaderBar badgeContent={6} />
          <Navbar activeIndex={4} />
        </Box>
      </Mobile>
    </Box>
  );
};

export default MyRecipePage;
