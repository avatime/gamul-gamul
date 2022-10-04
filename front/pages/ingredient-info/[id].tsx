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
import { BackHeader } from '../../src/components/BackHeader';

interface IProps {
  ingredientDetailInfo: IngredientDetailInfo;
  recipeList: RecipeInfo[];
  blackList: number[];
}

const IngredientInfoPage: NextPage<IProps> = ({
  ingredientDetailInfo,
  recipeList,
  blackList,
}) => {
 
  const router = useRouter();
  const { id } = router.query;
  const userName = getCookie("userName");
  const apiClient = ApiClient.getInstance();
  const [bookmark, setBookmark] = useState(false);
  const [basket, setBasket] = useState(false);
  const [showChild, setShowChild] = useState(false);

  useEffect(() => {
    apiClient.postIngredientView(Number(id as string));
  }, [apiClient, id]);

  const onClickBookmark = async () => {
    ApiClient.getInstance()
      .putBookmarkIngredient(userName, Number(id));
  };

  const onClickBasket = () => {
    ApiClient.getInstance()
      .putBasketIngredient(userName, Number(id));
    }

  useEffect(() => {
    if (ingredientDetailInfo.ingredient_info) {
      saveRecentSearchLocalStorage("ingredient", ingredientDetailInfo.ingredient_info.ingredient_id, ingredientDetailInfo.ingredient_info.name);
    }
  }, [ingredientDetailInfo]);

  useEffect(() => {
    if(getCookie("userName") != null) {
      ApiClient.getInstance()
        .getIngredientDetailInfo(Number(id), getCookie("userName"))
        .then((data) => {
          setBookmark(data.ingredient_info.bookmark);
          setBasket(data.ingredient_info.basket);
        });
    }
    setShowChild(true);
  }, []);

  if (!showChild) {
    return null;
  }

  if (typeof window === 'undefined') {
    return <></>;
  } else {
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
                      name={ingredientDetailInfo.ingredient_info.name}
                      bookmark={bookmark}
                      basket={basket}
                      onClickBookmark={onClickBookmark}
                      onClickBasket={onClickBasket}
                      views={ingredientDetailInfo.ingredient_info.views}
                      imagePath={`/assets/ingredientsImg/${id}.jpg`}
                    />
                  </Box>
                <IngredientPriceComp
                  ingredientDetailInfo={ingredientDetailInfo}
                  inputWidth={"90%"}
                  inputHeight={500}
                  blackList={blackList}
                />
                <RecipeListComp recipeList={recipeList} rowSize={2} gridSize={3} />
              </Grid>
              <Grid item xs={5}>
                <OfflineMartComp
                  ingredientInfo={ingredientDetailInfo.ingredient_info}
                  mapId="desktop"
                  inputHeight="300px"
                />
                <OnlineMarketInfoComp onlineMartInfo={ingredientDetailInfo.online_mart_info} iconSize="15px" />
              </Grid>
            </Grid>
          </Box>
        </Desktop>
        <Tablet>
          <Box className={styles.PageforTablet}>
            <Box marginTop="10px">
            <InfoTitle
              name={ingredientDetailInfo.ingredient_info.name}
              bookmark={bookmark}
              basket={basket}
              onClickBookmark={onClickBookmark}
              onClickBasket={onClickBasket}
              views={ingredientDetailInfo.ingredient_info.views}
              imagePath={`/assets/ingredientsImg/${id}.jpg`}
            />
            </Box>
            <IngredientPriceComp
              ingredientDetailInfo={ingredientDetailInfo}
              inputWidth={"95%"}
              inputHeight={450}
              blackList={blackList}
            />
            <RecipeListComp recipeList={recipeList} gridSize={4} />
            <OfflineMartComp
              ingredientInfo={ingredientDetailInfo.ingredient_info}
              mapId="tablet"
              inputHeight="350px"
            />
            <OnlineMarketInfoComp onlineMartInfo={ingredientDetailInfo.online_mart_info} iconSize="15px" />
          </Box>
        </Tablet>
        <Mobile>
        <BackHeader />
          <Box className={styles.PageforMobile}>
            <InfoTitle
              name={ingredientDetailInfo.ingredient_info.name}
              bookmark={bookmark}
              basket={basket}
              onClickBookmark={onClickBookmark}
              onClickBasket={onClickBasket}
              views={ingredientDetailInfo.ingredient_info.views}
              imagePath={`/assets/ingredientsImg/${id}.jpg`}
            />
            <IngredientPriceComp
              ingredientDetailInfo={ingredientDetailInfo}
              inputWidth={"95%"}
              inputHeight={400}
              blackList={blackList}
            />
            <RecipeListComp recipeList={recipeList} rowSize={1} />
            <OfflineMartComp
              ingredientInfo={ingredientDetailInfo.ingredient_info}
              mapId="mobile"
              inputHeight="300px"
            />
            <OnlineMarketInfoComp onlineMartInfo={ingredientDetailInfo.online_mart_info} iconSize="15px" />
          </Box>
        </Mobile>
      </Box>
    );
  }

  
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
    "",
  );
  const recipeList = apiClient.search(ingredientDetailInfo.ingredient_info.name);
  const blackList = await apiClient.getBlackList();
  return {
    props: {
      ingredientDetailInfo,
      recipeList,
      blackList,
    },
  };
};
