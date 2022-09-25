import { Box } from "@mui/material";
import { NextPage } from "next";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Navbar } from "../src/components/Navbar";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";

interface IProps {}

const IngredientPage: NextPage<IProps> = (props) => {
  return (
    <Box>
      <Desktop>
        <Box className={styles.PageforDesktop}></Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}></Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}></Box>
      </Mobile>
    </Box>
  );
};

export default IngredientPage;
