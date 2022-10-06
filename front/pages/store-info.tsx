import { Box, Grid } from "@mui/material";
import { NextPage } from "next";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";
import { OfflineMartDetailComp } from "../src/components/templates/OfflineMartDetailComp";
import { BackHeader } from "../src/components/BackHeader";
import { Page } from '../src/components/Page';
interface IProps {}

const StoreInfoPage: NextPage<IProps> = (props) => {
  return (
    <Page>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <Grid container sx={{ display: "flex", justifyContent: "center" }}>
            <Grid item xs={6} sx={{ margin: "20px" }}>
              <OfflineMartDetailComp inputHeight={"400px"} mapId="desktop" />
            </Grid>
          </Grid>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <Grid container sx={{ display: "flex", justifyContent: "center" }}>
            <Grid item xs={7} sx={{ margin: "20px" }}>
              <OfflineMartDetailComp inputHeight={"350px"} mapId="tablet" />
            </Grid>
          </Grid>
        </Box>
      </Tablet>
      <Mobile>
        <BackHeader />
        <Box className={styles.PageforMobile}>
          <OfflineMartDetailComp inputHeight={"300px"} mapId="mobile" />
        </Box>
      </Mobile>
    </Page>
  );
};

export default StoreInfoPage;
