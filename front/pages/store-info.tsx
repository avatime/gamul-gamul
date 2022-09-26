import { Box } from "@mui/material";
import { NextPage } from "next";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";
import { OfflineMartDetailComp } from '../src/components/templates/OfflineMartDetailComp';
interface IProps {}

const StoreInfoPage: NextPage<IProps> = (props) => {

  return (
    <Box className="page-background">
      <Desktop>
        <Box className={styles.PageforDesktop}></Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}></Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
            <OfflineMartDetailComp inputHeight={"300px"} mapId="mobile" />
        </Box>
      </Mobile>
    </Box>
  );
};

export default StoreInfoPage;