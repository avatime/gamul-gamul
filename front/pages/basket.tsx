import { Box, Grid } from "@mui/material";
import { NextPage } from "next";
import { ApiClient } from "../src/apis/apiClient";
import { RecipeOrderType } from "../src/apis/interfaces/recipeApi";
import { IngredientInfo } from "../src/apis/responses/ingredientInfo";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import { IngredientListComp } from "../src/components/templates/IngredientListComp";
import { RecipeInfo } from "../src/apis/responses/recipeInfo";
import styles from "../styles/Page.module.css";
import { RecipeListComp } from "../src/components/templates/RecipeListComp";
import { Page } from "../src/components/Page";
import { useState, useEffect } from 'react';
import { getCookie } from '../src/utils/cookie';

interface IProps {
  recipeListWithBasket: RecipeInfo[];
}

const BasketPage: NextPage<IProps> = ({
  recipeListWithBasket,
}) => {
  const [basketIngredientList, setBasketIngredientList] = useState<IngredientInfo[]>([]);
  const [totalPrice, setTotalPrice] = useState(0);

  useEffect(() => {
    ApiClient.getInstance()
      .getBasketIngredientList(getCookie("userName"))
      .then((data) => setBasketIngredientList(data));
  }, []);

  useEffect(() => {
    setTotalPrice(basketIngredientList.reduce((p, c) => p + c.price, 0));
  }, [basketIngredientList]);

  return (
    <Page>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <IngredientListComp
            title="바구니"
            ingredientList={basketIngredientList}
            gridSize={6}
            totalPrice={totalPrice}
          />
          <RecipeListComp type="row" title="요리법 with 바구니" recipeList={recipeListWithBasket} />
        </Box>
      </Desktop>
      {/* <Tablet>
        <Box className={styles.PageforTablet}>
          <IngredientListComp
            title="바구니"
            ingredientList={basketIngredientList}
            rowSize={1}
            gridSize={6}
            totalPrice={totalPrice}
          />
          <RecipeListComp type="row" title="요리법 with 바구니" recipeList={recipeListWithBasket} />
        </Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
          <IngredientListComp
            title="바구니"
            ingredientList={basketIngredientList}
            rowSize={1}
            totalPrice={totalPrice}
          />
          <RecipeListComp type="row" title="요리법 with 바구니" recipeList={recipeListWithBasket} />
        </Box>
      </Mobile> */}
    </Page>
  );
};

export default BasketPage;

export async function getServerSideProps() {
  const userName = "";
  const apiClient = ApiClient.getInstance();
  const recipeListWithBasket = await apiClient.getRecipeWithBasketList(
    userName,
    RecipeOrderType.NAME_ASC,
    1,
    100
  );

  return {
    props: {
      recipeListWithBasket,
    },
  };
}
