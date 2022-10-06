import { Box, Grid, Stack } from "@mui/material";
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
import { useState, useEffect } from "react";
import { getCookie } from "../src/utils/cookie";
import { ButtonOutlined } from "../src/components/button/ButtonOutlined";
import CameraAltIcon from "@mui/icons-material/CameraAlt";
import { convertFileToBase64 } from "../src/utils/fileUtil";
import Router from "next/router";

interface IProps {}

const BasketPage: NextPage<IProps> = ({}) => {
  const [basketIngredientList, setBasketIngredientList] = useState<IngredientInfo[]>([]);
  const [totalPrice, setTotalPrice] = useState(0);
  const [recipeListWithBasket, setRecipeListWithBasket] = useState<RecipeInfo[]>([]);

  useEffect(() => {
    if (getCookie("userName") != null) {
      ApiClient.getInstance()
        .getBasketIngredientList(getCookie("userName"))
        .then((data) => setBasketIngredientList(data));
      ApiClient.getInstance()
        .getRecipeWithBasketList(getCookie("userName"), RecipeOrderType.VIEW_ASC, 0, 20)
        .then((data) => setRecipeListWithBasket(data));
    }
  }, []);

  useEffect(() => {
    setTotalPrice(basketIngredientList.reduce((p, c) => p + c.price, 0));
  }, [basketIngredientList]);

  const onClickOCR = (e: any) => {
    if (e.target.files.length) {
      convertFileToBase64(e.target.files[0], (base64) => {
        ApiClient.getInstance()
          .postOCR(getCookie("userName"), base64)
          .then(() => Router.reload());
      });
    }
  };

  const OCRButton = () => (
    <form method="post" encType="multipart/form-data">
      <label>
        <Stack
          direction="row"
          justifyContent="center"
          alignItems="center"
          color="#4411AA"
          style={{
            backgroundColor: "white",
            borderRadius: "10px",
            height: "50px",
            margin: "15px",
            cursor: "pointer",
          }}
        >
          <p style={{ color: "black", fontSize: 16, fontWeight: "bold", marginRight: "5px" }}>
            영수증으로 바구니 담기
          </p>
          <CameraAltIcon />
        </Stack>
        <input type="file" accept="image/*" style={{ display: "none" }} onChange={onClickOCR} />
      </label>
    </form>
  );

  return (
    <Page>
      <Desktop>
        <Box
          className={styles.PageforDesktop}
          display="flex"
          flexDirection="column"
          alignItems="stretch"
        >
          <OCRButton />
          <IngredientListComp
            title="바구니"
            ingredientList={basketIngredientList}
            gridSize={6}
          />

          <RecipeListComp type="row" title="요리법 with 바구니" recipeList={recipeListWithBasket} />
        </Box>
      </Desktop>
      <Tablet>
        <Box
          className={styles.PageforTablet}
          display="flex"
          flexDirection="column"
          alignItems="stretch"
        >
          <OCRButton />
          <IngredientListComp
            title="바구니"
            ingredientList={basketIngredientList}
            rowSize={1}
            gridSize={6}
          />
          <RecipeListComp type="row" title="요리법 with 바구니" recipeList={recipeListWithBasket} />
        </Box>
      </Tablet>
      <Mobile>
        <Box
          className={styles.PageforMobile}
          display="flex"
          flexDirection="column"
          alignItems="stretch"
        >
          <OCRButton />
          <IngredientListComp
            title="바구니"
            ingredientList={basketIngredientList}
            rowSize={1}
          />
          <RecipeListComp type="row" title="요리법 with 바구니" recipeList={recipeListWithBasket} />
        </Box>
      </Mobile>
    </Page>
  );
};

export default BasketPage;

export async function getServerSideProps() {
  return {
    props: {},
  };
}
