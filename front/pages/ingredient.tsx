import { Box } from "@mui/material";
import { NextPage } from "next";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";
import { Page } from '../src/components/Page';

interface IProps {}

const IngredientPage: NextPage<IProps> = (props) => {
  return (
    <Page>
      <Desktop>
        <Box className={styles.PageforDesktop}></Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}></Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}></Box>
      </Mobile>
    </Page>
  );
};

export default IngredientPage;
