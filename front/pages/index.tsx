import type { NextPage } from "next";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import { Box, Grid } from "@mui/material";
import { ApiClient } from "../src/apis/apiClient";
import { IngredientOrderType } from "../src/apis/interfaces/ingredientApi";
import { RecipeOrderType } from "../src/apis/interfaces/recipeApi";
import { IngredientInfo } from "../src/apis/responses/ingredientInfo";
import { RecipeInfo } from "../src/apis/responses/recipeInfo";
import { MyRecipeInfo } from "../src/apis/responses/myRecipeInfo";
import { RecipeListComp } from "../src/components/templates/RecipeListComp";
import { IngredientListComp } from "../src/components/templates/IngredientListComp";
import { MyRecipeListComp } from "../src/components/templates/MyRecipeListComp";
import styles from "../styles/Page.module.css";
import { Page } from "../src/components/Page";

interface IProps {
  ingredientList: IngredientInfo[];
  recipeList: RecipeInfo[];
  myRecipeList: MyRecipeInfo[];
}

const MainPage: NextPage<IProps> = ({ ingredientList, recipeList, myRecipeList }) => {
  return (
    <Page>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <Grid container direction="row">
            <Grid item xs={8}>
              <IngredientListComp
                showMore
                rowSize={4}
                gridSize={6}
                ingredientList={ingredientList}
              />
            </Grid>
            <Grid item xs={4}>
              <RecipeListComp showMore rowSize={2} gridSize={3} recipeList={recipeList} />
              <MyRecipeListComp showMore rowSize={2} gridSize={3} myRecipeList={myRecipeList} />
            </Grid>
          </Grid>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <IngredientListComp showMore rowSize={2} gridSize={6} ingredientList={ingredientList} />
          <RecipeListComp showMore rowSize={2} gridSize={6} recipeList={recipeList} />
          <MyRecipeListComp showMore rowSize={2} gridSize={6} myRecipeList={myRecipeList} />
        </Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
          <IngredientListComp showMore rowSize={2} gridSize={3} ingredientList={ingredientList} />
          <RecipeListComp showMore rowSize={2} gridSize={3} recipeList={recipeList} />
          <MyRecipeListComp showMore rowSize={2} gridSize={3} myRecipeList={myRecipeList} />
        </Box>
      </Mobile>
    </Page>
  );
};

export default MainPage;

export const getStaticProps = async () => {
  const apiClient = ApiClient.getInstance();
  const ingredientList = await apiClient.getIngredientList(IngredientOrderType.VOLATILITY_ASC);
  const recipeList = await apiClient.getRecipeList(RecipeOrderType.VIEW_ASC, 1, 90);
  const myRecipeList = await apiClient.getMyRecipeList("userId");

  return {
    props: {
      ingredientList,
      recipeList,
      myRecipeList,
    },
  };
};
