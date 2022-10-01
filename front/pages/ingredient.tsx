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
import { getIngredientHighClassList } from "../src/apis/dummy/dummyApi";
import { HighClass } from "../src/apis/responses/highClass";
import { HighClassComp } from "../src/components/templates/HighClassComp";

interface IProps {
  wishList: IngredientInfo[];
  upIngredientList: IngredientInfo[];
  downIngredientList: IngredientInfo[];
  popularIngredientList: IngredientDetailInfo[];
  highClassList: HighClass[];
  ingredientList: IngredientInfo[];
}

const IngredientPage: NextPage<IProps> = ({
  wishList,
  upIngredientList,
  downIngredientList,
  popularIngredientList,
  highClassList,
  ingredientList,
}) => {
  const [userName, setUserName] = useState("");

  useEffect(() => {
    setUserName(getCookie("userName"));
  }, []);

  return (
    <Box>
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
          <HighClassComp
            highClassList={highClassList}
            ingredientList={ingredientList}
            gridSize={8}
          />
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
            ingredientList={downIngredientList}
            title="지금이 기회!"
            rowSize={1}
          />
          <IngredientListComp
            ingredientList={upIngredientList}
            title="오늘은 피해요!"
            rowSize={1}
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
          <HighClassComp highClassList={highClassList} ingredientList={ingredientList} />
        </Box>
      </Mobile>
    </Box>
  );
};

export default IngredientPage;

export const getStaticProps = async () => {
  const apiClient = ApiClient.getInstance();
  const userName: string = getCookie("userName");
  const wishList = await apiClient.getBookmarkIngredientList(userName);
  const ingredientList = await apiClient.getIngredientList(3, 0);
  const upIngredientList = new Array<IngredientInfo>();
  const downIngredientList = new Array<IngredientInfo>();
  const ingredientList2 = await apiClient.getIngredientList(2, 0);
  const popularIdList = new Array();
  const highClassList = await apiClient.getIngredientHighClassList();

  ingredientList.forEach((v) => {
    if (v.volatility > 0) {
      upIngredientList.push(v);
    } else if (v.volatility < 0) {
      downIngredientList.push(v);
    }
  });

  const popularIngredientList = new Array();

  for (var i = 0; i < 3; i++) {
    const id = ingredientList2[i].ingredient_id;
    const ingredientDetailInfo = await apiClient.getIngredientDetailInfo(id);
    popularIngredientList.push(ingredientDetailInfo);
  }

  return {
    props: {
      wishList: wishList,
      upIngredientList: upIngredientList,
      downIngredientList: downIngredientList,
      popularIngredientList: popularIngredientList,
      highClassList,
      ingredientList,
    },
  };
};
