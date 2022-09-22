import type { NextPage } from "next";
import Head from "next/head";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import { Backdrop, Box, Grid } from "@mui/material";
import { ApiClient } from "../src/apis/apiClient";
import { IngredientOrderType } from "../src/apis/interfaces/ingredientApi";
import { RecipeOrderType } from "../src/apis/interfaces/recipeApi";
import { IngredientInfo } from "../src/apis/responses/ingredientInfo";
import { RecipeInfo } from "../src/apis/responses/recipeInfo";
import { MyRecipeInfo } from "../src/apis/responses/myRecipeInfo";
import { RecipeListComp } from "../src/components/templates/RecipeListComp";
import { IngredientListComp } from "../src/components/templates/IngredientListComp";
import { MyRecipeListComp } from "../src/components/templates/MyRecipeListComp";
import { HeaderBar } from "../src/components/HeaderBar";
import { Navbar } from "../src/components/Navbar";
import styles from "../styles/Page.module.css";
import { useState } from "react";
import { SearchComp } from "../src/components/templates/SearchComp";

interface IProps {
  ingredientList: IngredientInfo[];
  recipeList: RecipeInfo[];
  myRecipeList: MyRecipeInfo[];
}

const MainPage: NextPage<IProps> = ({ ingredientList, recipeList, myRecipeList }) => {
  const [showSearch, setShowSearch] = useState<boolean>(false);
  const onClickSearch = () => {
    setShowSearch(true);
  };
  const onCloseSearch = () => {
    setShowSearch(false);
  }
  return (
    <Box className="page-background">
      <Head>
        <title>가물가물</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <Desktop>
        <HeaderBar badgeContent={6} onClickSearch={onClickSearch} />
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
        <Navbar activeIndex={0} />
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <HeaderBar badgeContent={6} onClickSearch={onClickSearch} />
          <IngredientListComp showMore rowSize={2} gridSize={6} ingredientList={ingredientList} />
          <RecipeListComp showMore rowSize={2} gridSize={6} recipeList={recipeList} />
          <MyRecipeListComp showMore rowSize={2} gridSize={6} myRecipeList={myRecipeList} />

          <Navbar activeIndex={0} />
        </Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
          <HeaderBar badgeContent={6} onClickSearch={onClickSearch} />
          <IngredientListComp showMore rowSize={2} gridSize={3} ingredientList={ingredientList} />
          <RecipeListComp showMore rowSize={2} gridSize={3} recipeList={recipeList} />
          <MyRecipeListComp showMore rowSize={2} gridSize={3} myRecipeList={myRecipeList} />
          <Navbar activeIndex={0} />
        </Box>
      </Mobile>
      <Backdrop
        sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={showSearch}
      >
        <SearchComp onCloseSearch={onCloseSearch} />
      </Backdrop>
    </Box>
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