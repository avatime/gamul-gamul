import { Box, Grid } from "@mui/material";
import { NextPage } from "next";
import { ApiClient } from "../src/apis/apiClient";
import { RecipeOrderType } from "../src/apis/interfaces/recipeApi";
import { RecipeInfo } from "../src/apis/responses/recipeInfo";
import { YoutubeInfo } from "../src/apis/responses/youtubeInfo";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import { RecipeListComp } from "../src/components/templates/RecipeListComp";
import { YoutubeRecipeListComp } from "../src/components/templates/YoutubeRecipeListComp";
import styles from "../styles/Page.module.css";
import { Page } from '../src/components/Page';

interface IProps {
  bookmarkRecipeList: RecipeInfo[];
  popularRecipeList: RecipeInfo[];
  recipeWithBasketList: RecipeInfo[];
  popularYoutubeList: YoutubeInfo[];
}

const RecipePage: NextPage<IProps> = ({
  bookmarkRecipeList,
  popularRecipeList,
  recipeWithBasketList,
  popularYoutubeList,
}) => {
  return (
    <Page>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <RecipeListComp title="찜 목록" rowSize={1} gridSize={6} recipeList={bookmarkRecipeList} />
          <RecipeListComp title="인기 요리법" rowSize={1}gridSize={6} recipeList={popularRecipeList} />
          <Grid container>
            <Grid item xs={4}>
              <RecipeListComp
                type="row"
                title="요리법 with 바구니"
                recipeList={recipeWithBasketList}
              />
            </Grid>
            <Grid item xs={8}>
              <YoutubeRecipeListComp
                title="인기 요리법 유튜브"
                youtubeInfoList={popularYoutubeList}
                gridSize={2}
              />
            </Grid>
          </Grid>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <RecipeListComp title="찜 목록" rowSize={1} gridSize={6} recipeList={bookmarkRecipeList} />
          <RecipeListComp title="인기 요리법" rowSize={1}gridSize={6} recipeList={popularRecipeList} />
          <Grid container>
            <Grid item xs>
              <RecipeListComp
                type="row"
                title="요리법 with 바구니"
                recipeList={recipeWithBasketList}
              />
            </Grid>
            <Grid item xs>
              <YoutubeRecipeListComp
                title="인기 요리법 유튜브"
                youtubeInfoList={popularYoutubeList}
              />
            </Grid>
          </Grid>
        </Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
          <RecipeListComp title="찜 목록" rowSize={1} recipeList={bookmarkRecipeList} />
          <RecipeListComp title="인기 요리법" rowSize={1} recipeList={popularRecipeList} />
          <RecipeListComp type="row" title="요리법 with 바구니" recipeList={recipeWithBasketList} />
          <YoutubeRecipeListComp title="인기 요리법 유튜브" youtubeInfoList={popularYoutubeList} />
        </Box>
      </Mobile>
    </Page>
  );
};

export default RecipePage;

export async function getServerSideProps() {
  const userName = "";
  const apiClient = ApiClient.getInstance();
  const bookmarkRecipeList = await apiClient.getBookmarkRecipeList(userName);
  const popularRecipeList = await apiClient.getRecipeList(RecipeOrderType.VIEW_ASC, 1, 50);
  const recipeWithBasketList = await apiClient.getRecipeWithBasketList(
    userName,
    RecipeOrderType.VIEW_ASC,
    1,
    50
  );
  const popularYoutubeList = await apiClient.getPopularYoutubeList();

  return {
    props: {
      bookmarkRecipeList,
      popularRecipeList,
      recipeWithBasketList,
      popularYoutubeList,
    },
  };
}
