import { NextPage } from "next";
import { ApiClient } from "../src/apis/apiClient";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import { IngredientInfo } from "../src/apis/responses/ingredientInfo";
import { RecipeInfo } from "../src/apis/responses/recipeInfo";
import { getCookie } from "../src/utils/cookie";
import { Box } from "@mui/material";
import styles from "../styles/Page.module.css";
import { BackHeader } from "../src/components/BackHeader";
import { IngredientListComp } from "../src/components/templates/IngredientListComp";
import { RecipeListComp } from "../src/components/templates/RecipeListComp";
import { Page } from '../src/components/Page';

interface IProps {
  bookmarkIngredientList: IngredientInfo[];
  bookmarkRecipeList: RecipeInfo[];
}

const WishListPage: NextPage<IProps> = ({ bookmarkIngredientList, bookmarkRecipeList }) => {
  return (
    <Page>
      <Desktop>
      <Box className={styles.PageforDesktop}>
          <IngredientListComp
            title="식재료 찜 목록"
            ingredientList={bookmarkIngredientList}
            rowSize={2}
            gridSize={6}
          />
          <RecipeListComp
            title="요리법 찜 목록"
            recipeList={bookmarkRecipeList}
            rowSize={2}
            gridSize={6}
          />
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          <IngredientListComp
            title="식재료 찜 목록"
            ingredientList={bookmarkIngredientList}
            rowSize={2}
            gridSize={6}
          />
          <RecipeListComp
            title="요리법 찜 목록"
            recipeList={bookmarkRecipeList}
            rowSize={2}
            gridSize={6}
          />
        </Box>
      </Tablet>
      <Mobile>
        <BackHeader text="찜 목록" />
        <Box className={styles.PageforMobile}>
          <IngredientListComp
            title="식재료 찜 목록"
            ingredientList={bookmarkIngredientList}
            rowSize={2}
            gridSize={3}
          />
          <RecipeListComp
            title="요리법 찜 목록"
            recipeList={bookmarkRecipeList}
            rowSize={2}
            gridSize={3}
          />
        </Box>
      </Mobile>
    </Page>
  );
};

export default WishListPage;

export async function getServerSideProps() {
  const userName = getCookie("userName");
  const apiClient = ApiClient.getInstance();
  const bookmarkIngredientList = await apiClient.getBookmarkIngredientList(userName);
  const bookmarkRecipeList = await apiClient.getBookmarkRecipeList(userName);

  return {
    props: {
      bookmarkIngredientList,
      bookmarkRecipeList,
    },
  };
}
