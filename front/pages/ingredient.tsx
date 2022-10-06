import { Box, Grid } from "@mui/material";
import { NextPage } from "next";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Navbar } from "../src/components/Navbar";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";
import { ApiClient } from "../src/apis/apiClient";
import { getCookie } from "../src/utils/cookie";
import { IngredientInfo } from "../src/apis/responses/ingredientInfo";
import { IngredientListComp } from "../src/components/templates/IngredientListComp";
import { useState, useEffect } from "react";
import { IngredientBarGraphComp } from "../src/components/templates/IngredientBarGraphComp";
import { IngredientDetailInfo } from "../src/apis/responses/ingredientDetailInfo";
import { CardContainer } from "../src/components/CardContainer";
import { HighClass } from "../src/apis/responses/highClass";
import { HighClassComp } from "../src/components/templates/HighClassComp";
import { Page } from "../src/components/Page";

interface IProps {
  upIngredientList: IngredientInfo[];
  downIngredientList: IngredientInfo[];
  popularIngredientList: IngredientDetailInfo[];
  highClassList: HighClass[];
  ingredientList: IngredientInfo[];
  ingredientList2: IngredientInfo[];
}

const IngredientPage: NextPage<IProps> = ({
  upIngredientList,
  downIngredientList,
  popularIngredientList,
  highClassList,
  ingredientList,
  ingredientList2,
}) => {
  const [userName, setUserName] = useState("");
  const [wishList, setWishList] = useState<IngredientInfo[]>([]);

  useEffect(() => {
    setUserName(getCookie("userName"));
    if(getCookie("userName") != null) {
      ApiClient.getInstance()
      .getBookmarkIngredientList(getCookie("userName"))
      .then((data) => setWishList(data));
    }
  }, []);

  return (
    <Page>
      <Desktop>
        <Box className={styles.PageforDesktop}>
          <Grid container>
            <Grid item xs={7}>
              {!!userName && (
                <IngredientListComp
                  ingredientList={wishList}
                  title="찜 목록"
                  rowSize={1}
                  gridSize={6}
                />
              )}
              <IngredientListComp
                ingredientList={ingredientList2}
                title="식재료 목록"
                gridSize={6}
              />
              <IngredientListComp
                ingredientList={downIngredientList}
                title="지금이 기회!"
                rowSize={1}
                gridSize={6}
              />
              <IngredientListComp
                ingredientList={upIngredientList}
                title="오늘은 피해요!"
                rowSize={1}
                gridSize={6}
              />
              <HighClassComp
            highClassList={highClassList}
            ingredientList={ingredientList}
            gridSize={5}
          />
            </Grid>
            <Grid item xs={5}>
              <CardContainer title="주요 물품 현황">
                <Box>
                  {popularIngredientList.map((data, index) => {
                    return (
                      <IngredientBarGraphComp
                        key={index}
                        ingredientInfo={data.ingredient_info}
                        priceTransitionInfo={data.price_transition_info}
                      />
                    );
                  })}
                </Box>
              </CardContainer>
            </Grid>
          </Grid>
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          {!!userName && (
            <IngredientListComp
              ingredientList={wishList}
              title="찜 목록"
              rowSize={1}
              gridSize={6}
            />
          )}
          <IngredientListComp
                ingredientList={ingredientList2}
                title="식재료 목록"
                gridSize={6}
              />
          <IngredientListComp
            ingredientList={downIngredientList}
            title="지금이 기회!"
            rowSize={1}
            gridSize={6}
          />
          <IngredientListComp
            ingredientList={upIngredientList}
            title="오늘은 피해요!"
            rowSize={1}
            gridSize={6}
          />
          <CardContainer title="주요 물품 현황">
            <Box>
              {popularIngredientList.map((data, index) => {
                return (
                  <IngredientBarGraphComp
                    key={index}
                    ingredientInfo={data.ingredient_info}
                    priceTransitionInfo={data.price_transition_info}
                  />
                );
              })}
            </Box>
          </CardContainer>
          <HighClassComp
            highClassList={highClassList}
            ingredientList={ingredientList}
            gridSize={4}
          />
        </Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
          {!!userName && (
            <IngredientListComp ingredientList={wishList} title="찜 목록" rowSize={1} />
          )}
          <IngredientListComp
            ingredientList={ingredientList2}
            title="식재료 목록"
          />
          <IngredientListComp
            ingredientList={downIngredientList}
            title="지금이 기회!"
            rowSize={1}
          />
          <IngredientListComp
            ingredientList={upIngredientList}
            title="오늘은 피해요!"
            rowSize={1}
          />
          <HighClassComp highClassList={highClassList} ingredientList={ingredientList} />
          <CardContainer title="주요 물품 현황">
            <Box>
              {popularIngredientList.map((data, index) => {
                return (
                  <IngredientBarGraphComp
                    key={index}
                    ingredientInfo={data.ingredient_info}
                    priceTransitionInfo={data.price_transition_info}
                  />
                );
              })}
            </Box>
          </CardContainer>
        </Box>
      </Mobile>
    </Page>
  );
};

export default IngredientPage;

export const getStaticProps = async () => {
  const apiClient = ApiClient.getInstance();
  const ingredientList = await apiClient.getIngredientList(3);
  const ingredientList2 = await apiClient.getIngredientList(1);
  const upIngredientList = new Array<IngredientInfo>();
  const downIngredientList = new Array<IngredientInfo>();
  const highClassList = await apiClient.getIngredientHighClassList();

  ingredientList.forEach((v) => {
    if (v.volatility > 0) {
      upIngredientList.push(v);
    } else if (v.volatility < 0) {
      downIngredientList.push(v);
    }
  });

  const popularIngredientList = new Array();

    const ingredientDetailInfo1 = await apiClient.getIngredientDetailInfo(27, "");
    popularIngredientList.push(ingredientDetailInfo1);
    const ingredientDetailInfo2 = await apiClient.getIngredientDetailInfo(28, "");
    popularIngredientList.push(ingredientDetailInfo2);
    const ingredientDetailInfo3 = await apiClient.getIngredientDetailInfo(66, "");
    popularIngredientList.push(ingredientDetailInfo3);
    const ingredientDetailInfo4 = await apiClient.getIngredientDetailInfo(71, "");
    popularIngredientList.push(ingredientDetailInfo4);
    const ingredientDetailInfo5 = await apiClient.getIngredientDetailInfo(72, "");
    popularIngredientList.push(ingredientDetailInfo5);

  return {
    props: {
      upIngredientList: upIngredientList,
      downIngredientList: downIngredientList,
      popularIngredientList: popularIngredientList,
      highClassList,
      ingredientList,
      ingredientList2,
    },
  };
};
