import { NextPage } from "next";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { saveRecentSearchLocalStorage, RecentSearch } from "../../src/utils/localStorageUtil";
import { ApiClient } from "../../src/apis/apiClient";
import { Box } from "@mui/system";
import { Desktop } from "../../src/components/Desktop";
import { Navbar } from "../../src/components/Navbar";
import styles from "../../styles/Page.module.css";
import { IngredientPriceComp } from "../../src/components/templates/IngredientPriceComp";
import { OfflineMartComp } from "../../src/components/templates/OfflineMartComp";
import { IngredientDetailInfo } from "../../src/apis/responses/ingredientDetailInfo";
import { IngredientInfo } from "../../src/apis/responses/ingredientInfo";
import { Tablet } from "../../src/components/Tablet";
import { Mobile } from "../../src/components/Mobile";
import { InfoTitle } from "../../src/components/InfoTitle";
import { getCookie } from "../../src/utils/cookie";
import { Grid } from "@mui/material";
import { OnlineMarketInfoComp } from '../../src/components/OnlineMarketInfoComp';
import { OnlineMartInfo } from '../../src/apis/responses/onlineMartInfo';

interface IProps {
  ingredientDetailInfo: IngredientDetailInfo;
  ingredientInfo: IngredientInfo;
  onlineMartInfo: OnlineMartInfo[];
}

const IngredientInfoPage: NextPage<IProps> = ({ ingredientDetailInfo, ingredientInfo, onlineMartInfo }) => {
  const router = useRouter();
  const { id } = router.query;
  const userName = getCookie("userName");
  const apiClient = ApiClient.getInstance();

  const setBookmark = async () => {
    await apiClient.putBookmarkIngredient(userName, Number(id));
  };

  useEffect(() => {
    saveRecentSearchLocalStorage("ingredient", ingredientInfo.ingredient_id, ingredientInfo.name);
  }, [ingredientInfo.ingredient_id, ingredientInfo.name]);

  return (
    <Box className="page-background">
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <Grid container>
            <Grid item xs={7}>
              <Grid container>
                <Grid
                  item
                  xs={6}
                  height="100px"
                  sx={{
                    display: "flex",
                    justifyContent: "center",
                    flexDirection: "column",
                  }}
                >
                  <InfoTitle
                    name={ingredientInfo.name}
                    bookmark={ingredientInfo.bookmark}
                    onClickBookmark={setBookmark}
                    views={ingredientDetailInfo.views}
                    imagePath={`/assets/ingredientsImg/${id}.jpg`}
                  />
                </Grid>
                <Grid item xs={6}>
                  {/* RecipePreviewComp */}
                </Grid>
              </Grid>
              <IngredientPriceComp
                ingredientDetailInfo={ingredientDetailInfo}
                inputWidth={"95%"}
                inputHeight={600}
              />
            </Grid>
            <Grid item xs={5}>
              <OfflineMartComp
                ingredientInfo={ingredientInfo}
                mapId="desktop"
                inputHeight="350px"
              />
              <OnlineMarketInfoComp onlineMartInfo={onlineMartInfo} width="95%" iconSize="15px" />
            </Grid>
          </Grid>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <Grid container>
            <Grid item xs={7}>
              <Grid container>
                <Grid
                  item
                  xs={6}
                  height="100px"
                  sx={{
                    display: "flex",
                    justifyContent: "center",
                    flexDirection: "column",
                  }}
                >
                  <InfoTitle
                    name={ingredientInfo.name}
                    bookmark={ingredientInfo.bookmark}
                    onClickBookmark={setBookmark}
                    views={ingredientDetailInfo.views}
                    imagePath={`/assets/ingredientsImg/${id}.jpg`}
                  />
                </Grid>
                <Grid item xs={6}>
                  {/* RecipePreviewComp */}
                </Grid>
              </Grid>
              <IngredientPriceComp
                ingredientDetailInfo={ingredientDetailInfo}
                inputWidth={"95%"}
                inputHeight={550}
              />
            </Grid>
            <Grid item xs={5}>
              <OfflineMartComp
                ingredientInfo={ingredientInfo}
                mapId="tablet"
                inputHeight="300px"
              />
              <OnlineMarketInfoComp onlineMartInfo={onlineMartInfo} width="95%" iconSize="15px" />
            </Grid>
          </Grid>
        </Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
          <InfoTitle
            name={ingredientInfo.name}
            bookmark={ingredientInfo.bookmark}
            onClickBookmark={setBookmark}
            views={ingredientDetailInfo.views}
            imagePath={`/assets/ingredientsImg/${id}.jpg`}
          />
          <IngredientPriceComp
            ingredientDetailInfo={ingredientDetailInfo}
            inputWidth={"95%"}
            inputHeight={500}
          />
          {/* RecipePreviewComp */}
          <OfflineMartComp
            ingredientInfo={ingredientInfo}
            mapId="mobile"
            inputHeight="300px"
          />
          <OnlineMarketInfoComp onlineMartInfo={onlineMartInfo} width="95%" iconSize="15px" />
        </Box>
      </Mobile>
    </Box>
  );
};

export default IngredientInfoPage;

export const getServerSideProps = async (context: any) => {
  const apiClient = ApiClient.getInstance();
  const ingredientDetailInfo = await apiClient.getIngredientDetailInfo(context.params.id);
  const ingredientInfo = ingredientDetailInfo.ingredient_info;
  const onlineMartInfo = ingredientDetailInfo.online_mart_info;

  return {
    props: {
      ingredientDetailInfo,
      ingredientInfo,
      onlineMartInfo,
    },
  };
};
