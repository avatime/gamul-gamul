import { Box, Grid } from "@mui/material";
import { NextPage } from "next";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import { MyRecipeComp } from "../src/components/templates/MyRecipeComp";
import styles from "../styles/Page.module.css";
import { MyRecipeInfo } from "../src/apis/responses/myRecipeInfo";
import { ApiClient } from "../src/apis/apiClient";
import { getCookie } from "../src/utils/cookie";
import { useEffect, useState } from "react";
import { BackHeader } from "../src/components/BackHeader";
import { Page } from '../src/components/Page';

interface IProps {}

const MyRecipePage: NextPage<IProps> = () => {
  const [myRecipeList, setMyRecipeList] = useState<MyRecipeInfo[]>([]);
  useEffect(() => {
    ApiClient.getInstance()
      .getMyRecipeList(getCookie("userName"))
      .then((data) => setMyRecipeList(data));
  }, []);

  return (
    <Page>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <Grid container sx={{ display: "flex", justifyContent: "center" }}>
            <Grid item xs={7} sx={{ margin: "20px" }}>
              <MyRecipeComp myRecipeList={myRecipeList} />
            </Grid>
          </Grid>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <Grid container sx={{ display: "flex", justifyContent: "center" }}>
            <Grid item xs={8} sx={{ margin: "20px" }}>
              <MyRecipeComp myRecipeList={myRecipeList} />
            </Grid>
          </Grid>
        </Box>
      </Tablet>
      <Mobile>
        <BackHeader />
        <Box className={styles.PageforMobile}>
          <MyRecipeComp myRecipeList={myRecipeList} />
        </Box>
      </Mobile>
    </Page>
  );
};

export default MyRecipePage;
