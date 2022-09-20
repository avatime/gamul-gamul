import React, { FC, Fragment, useEffect, useState } from 'react'
import IngredientPriceGraph from '../src/components/graph/IngredientPriceGraph';
import { PriceTransitionInfo } from '../src/apis/responses/priceTransitionInfo';
import { getIngredientDetailInfo } from '../src/apis/dummy/dummyApi';
import { ApiClient } from '../src/apis/apiClient';
import { NextPage } from 'next';
import { IngredientDetailInfo } from '../src/apis/responses/ingredientDetailInfo';
import { IngredientPriceComp } from '../src/components/graph/IngredientPriceComp';
import { Box } from '@mui/material';
import { grey } from '@mui/material/colors';

interface IProps {
  ingredientDetailInfo: IngredientDetailInfo
}

const GraphTest:NextPage<IProps> = ({ingredientDetailInfo}) => {

  return (
    <Box style={{background: grey[100]}}>
        <IngredientPriceComp ingredientDetailInfo={ingredientDetailInfo}></IngredientPriceComp>
    </Box>
   )
 }

export default GraphTest;

export async function getServerSideProps() {
  const apiClient = ApiClient.getInstance();
  const ingredientDetailInfo = await apiClient.getIngredientDetailInfo(1);
  return {
    props: {
      ingredientDetailInfo
    }
  }
}