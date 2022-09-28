import { Box, Grid } from '@mui/material';
import { NextPage } from "next";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Navbar } from "../src/components/Navbar";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";
import { ApiClient } from '../src/apis/apiClient';
import { getCookie } from '../src/utils/cookie';
import { IngredientInfo } from '../src/apis/responses/ingredientInfo';
import { IngredientListComp } from '../src/components/templates/IngredientListComp';
import { useState, useEffect } from 'react';

interface IProps {
  wishList: IngredientInfo[];
  upIngredientList: IngredientInfo[];
  downIngredientList: IngredientInfo[];
}

const IngredientPage: NextPage<IProps> = ({ wishList, upIngredientList, downIngredientList }) => {
  const [userName, setUserName] = useState("");
  
  useEffect(() => {
    setUserName(getCookie("userName"));
  }, []);

  return (
    <Box>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <Grid container>
            <Grid item xs={8}>
            {!!userName && <IngredientListComp ingredientList={wishList} title="찜 목록" gridSize={4} />}
            <IngredientListComp ingredientList={downIngredientList} title="지금이 기회!" gridSize={4} />
            <IngredientListComp ingredientList={upIngredientList} title="오늘은 피해요!" gridSize={4} />
            </Grid>
            <Grid item xs={4}>
              <Box>
                {/* 주요 물품 현황 */}
                {/* 종류별 상세 조회 */}
              </Box>
            </Grid>
          </Grid>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
        <Grid container>
            <Grid item xs={7}>
            {!!userName && <IngredientListComp ingredientList={wishList} title="찜 목록" />}
            <IngredientListComp ingredientList={downIngredientList} title="지금이 기회!" />
            <IngredientListComp ingredientList={upIngredientList} title="오늘은 피해요!" />
            </Grid>
            <Grid item xs={5}>
              <Box>
                {/* 주요 물품 현황 */}
                {/* 종류별 상세 조회 */}
              </Box>
            </Grid>
          </Grid>
        </Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
          {!!userName && <IngredientListComp ingredientList={wishList} title="찜 목록" rowSize={1} />}
          <IngredientListComp ingredientList={downIngredientList} title="지금이 기회!" rowSize={1} />
          <IngredientListComp ingredientList={upIngredientList} title="오늘은 피해요!" rowSize={1} />
          {/* 주요 물품 현황 */}
          {/* 종류별 상세 조회 */}
        </Box>
      </Mobile>
    </Box>
  );
};

export default IngredientPage;

export const getStaticProps = async () => {
  const apiClient = ApiClient.getInstance();
  const userName: string = getCookie("userName");
  const wishList = await apiClient.getBookmarkIngredientList(userName);
  const ingredientList = await apiClient.getIngredientList(3, 0);
  const upIngredientList = new Array<IngredientInfo>();
  const downIngredientList = new Array<IngredientInfo>();
  ingredientList.forEach((v) => {
    if(v.volatility > 0) {
      upIngredientList.push(v);
    } else if(v.volatility < 0) {
      downIngredientList.push(v);
    }
  });

  return {
    props: {
      wishList: wishList,
      upIngredientList: upIngredientList,
      downIngredientList: downIngredientList,
    },
  };
};
