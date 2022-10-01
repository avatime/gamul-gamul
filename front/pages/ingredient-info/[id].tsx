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
  onlineMartInfo: OnlineMartInfo[];
  recipeList: RecipeInfo[];
}

const IngredientInfoPage: NextPage<IProps> = ({
  ingredientDetailInfo,
  ingredientInfo,
  onlineMartInfo,
  recipeList,
}) => {
 

  const router = useRouter();
  const { id } = router.query;
  const userName = getCookie("userName");
  const apiClient = ApiClient.getInstance();

  useEffect(() => {
    apiClient.postIngredientView(Number(id as string));
  }, [apiClient, id]);

  const setBookmark = async () => {
    await apiClient.putBookmarkIngredient(userName, Number(id));
  };

  const onClickBasket = () => {
    apiClient.putBasketIngredient(userName, Number(id));
  }

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
                    onClickBasket={onClickBasket}
                    views={ingredientDetailInfo.views}
                    imagePath={`/assets/ingredientsImg/${id}.jpg`}
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
          <Box marginTop="10px">
          <InfoTitle
            name={ingredientInfo.name}
            bookmark={ingredientInfo.bookmark}
            onClickBookmark={setBookmark}
            onClickBasket={onClickBasket}
            views={ingredientDetailInfo.views}
            imagePath={`/assets/ingredientsImg/${id}.jpg`}
          />
          </Box>
          <IngredientPriceComp
            ingredientDetailInfo={ingredientDetailInfo}
            inputWidth={"95%"}
            inputHeight={500}
          />
          <RecipeListComp recipeList={recipeList} gridSize={4} />
          <OfflineMartComp
            ingredientInfo={ingredientInfo}
            mapId="tablet"
            inputHeight="350px"
          />
          <OnlineMarketInfoComp onlineMartInfo={onlineMartInfo} width="95%" iconSize="15px" />
        </Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
          <InfoTitle
            name={ingredientInfo.name}
            bookmark={ingredientInfo.bookmark}
            onClickBookmark={setBookmark}
            onClickBasket={onClickBasket}
            views={ingredientDetailInfo.views}
            imagePath={`/assets/ingredientsImg/${id}.jpg`}
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

export async function getStaticPaths() {
  const lastId = 72;
  const paths = Array.from({ length: lastId }, (_, i) => i + 1).map((id) => ({
    params: {
      id: id.toString(),
    },
  }));
  return {
    paths,
    fallback: false,
  };
}

export const getStaticProps = async (context: any) => {
  const apiClient = ApiClient.getInstance();
  const ingredientDetailInfo = await apiClient.getIngredientDetailInfo(
    context.params.id,
  );
  const ingredientInfo = ingredientDetailInfo.ingredient_info;
  const onlineMartInfo = ingredientDetailInfo.online_mart_info;
  // const recipeList = await apiClient.search(ingredientInfo.name); // api 구현시 적용
  const recipeList = await apiClient.getRecipeList(1, 1, 20);
  return {
    props: {
      ingredientDetailInfo,
      ingredientInfo,
      onlineMartInfo,
      recipeList,
    },
  };
};
