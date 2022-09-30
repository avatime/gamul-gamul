import { NextPage } from "next";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import {
  saveRecentSearchLocalStorage,
  RecentSearch,
} from "../../src/utils/localStorageUtil";
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
import { RecipeInfo } from '../../src/apis/responses/recipeInfo';
import { RecipeListComp } from '../../src/components/templates/RecipeListComp';

interface IProps {
  ingredientDetailInfo: IngredientDetailInfo;
  ingredientInfo: IngredientInfo;
  imagePath: string;
  views: number;
  onlineMartInfo: OnlineMartInfo[];
  recipeList: RecipeInfo[];
}

const IngredientInfoPage: NextPage<IProps> = ({
  ingredientDetailInfo,
  ingredientInfo,
  imagePath,
  views,
  onlineMartInfo,
  recipeList,
}) => {
  const router = useRouter();
  const { id } = router.query;
  const userName = getCookie("userName");
  const apiClient = ApiClient.getInstance();

  const setBookmark = async () => {
    await apiClient.putBookmarkIngredient(userName, Number(id));
  };

  useEffect(() => {
    if (id) {
      saveRecentSearchLocalStorage("ingredient", +(id as string), `이름 ${id}`);
    }
  }, [id]);

  return (
    <Box>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <Grid container>
            <Grid item xs={7}>
                <Box
                  height="175px"
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
                    views={views}
                    imagePath={imagePath}
                  />
                </Box>
              <IngredientPriceComp
                ingredientDetailInfo={ingredientDetailInfo}
                inputWidth={"95%"}
                inputHeight={650}
              />
            </Grid>
            <Grid item xs={5}>
            <RecipeListComp recipeList={recipeList} rowSize={2} gridSize={3} />
              <OfflineMartComp
                ingredientInfo={ingredientInfo}
                mapId="desktop"
                inputHeight="300px"
              />
              <OnlineMarketInfoComp onlineMartInfo={onlineMartInfo} width="95%" iconSize="15px" />
            </Grid>
          </Grid>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
        <Grid container>
            <Grid item xs={6}>
              <Box
                  height="175px"
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
                    views={views}
                    imagePath={imagePath}
                  />
                </Box>
              <IngredientPriceComp
                ingredientDetailInfo={ingredientDetailInfo}
                inputWidth={"95%"}
                inputHeight={650}
              />
            </Grid>
            <Grid item xs={6}>
            <RecipeListComp recipeList={recipeList} rowSize={2} gridSize={2} />
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
            views={views}
            imagePath={imagePath}
          />
          <IngredientPriceComp
            ingredientDetailInfo={ingredientDetailInfo}
            inputWidth={"95%"}
            inputHeight={500}
          />
          <RecipeListComp recipeList={recipeList} rowSize={1} />
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
  const ingredientDetailInfo = await apiClient.getIngredientDetailInfo(
    context.params.id,
  );
  const ingredientInfo = ingredientDetailInfo.ingredient_info;
  const imagePath = "/test_hamburger.jpg"; // ingredientid 가지고 imagepath 설정
  const views = 100; // ingredientid 가지고 views 알아내는 api 호출
  const onlineMartInfo = ingredientDetailInfo.online_mart_info;
  // const recipeList = await apiClient.search(ingredientInfo.name);
  const recipeList = await apiClient.getRecipeList(1, 1, 20);
  return {
    props: {
      ingredientDetailInfo,
      ingredientInfo,
      imagePath,
      views,
      onlineMartInfo,
      recipeList,
    },
  };
};
