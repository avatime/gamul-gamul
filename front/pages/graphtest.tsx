import React, { FC, Fragment, useEffect, useState } from 'react'
import { ApiClient } from '../src/apis/apiClient';
import { NextPage } from 'next';
import { IngredientDetailInfo } from '../src/apis/responses/ingredientDetailInfo';
import { IngredientPriceComp } from '../src/components/templates/IngredientPriceComp';
import { Box } from '@mui/material';
import { Mobile } from '../src/components/Mobile';
import styles from "../styles/Page.module.css"
import { HeaderBar } from '../src/components/HeaderBar';
import { Navbar } from '../src/components/Navbar';
import { MyRecipeInfo } from '../src/apis/responses/myRecipeInfo';
import { MyRecipeComp } from '../src/components/templates/MyRecipeComp';
import { OfflineMartComp } from '../src/components/templates/OfflineMartComp';

interface IProps {
  ingredientDetailInfo: IngredientDetailInfo;
  myRecipeList: MyRecipeInfo[];
}

const GraphTest:NextPage<IProps> = ({ingredientDetailInfo, myRecipeList}) => {

  const test = () => {
    console.log(test);
  }

  return (
    <Box className="page-background">
      <Mobile>
        <HeaderBar badgeContent={6} onClickSearch={test}/>
        <Box className={styles.PageforMobile}>
        <IngredientPriceComp ingredientDetailInfo={ingredientDetailInfo} inputWidth={"95%"} inputHeight={300}></IngredientPriceComp>
        <OfflineMartComp ingredientInfo={ingredientDetailInfo.ingredient_info} />
        <MyRecipeComp myRecipeList={myRecipeList} />
        <Navbar activeIndex={1} />
        </Box>
      </Mobile>
    </Box>
   )
 }

export default GraphTest;

export async function getServerSideProps() {
  const apiClient = ApiClient.getInstance();
  const ingredientDetailInfo = await apiClient.getIngredientDetailInfo(1);
  const myRecipeList = await apiClient.getMyRecipeList("userId");
  return {
    props: {
      ingredientDetailInfo,
      myRecipeList
    }
  }
}