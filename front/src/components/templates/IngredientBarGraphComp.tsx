import React, { FC, useEffect, useState } from 'react'
import { Box } from '@mui/system';
import { Grid } from '@mui/material';
import { IngredientInfo } from '../../apis/responses/ingredientInfo';
import IngredientPriceGraph from '../IngredientPriceGraph';
import { PriceTransitionInfo } from '../../apis/responses/priceTransitionInfo';

interface IProps {
    ingredientInfo: IngredientInfo;
    priceTransitionInfo: PriceTransitionInfo;
    isExternerImage?: boolean;
}

export const IngredientBarGraphComp:FC<IProps> = ({ ingredientInfo, priceTransitionInfo }) => {

  return (
    <Grid container sx={{margin: "15px 0px 0px 0px"}}>
        <Grid item xs={2} sx={{display: "flex", flexDirection: "column", justifyContent: "center", }}>
            {/* <Image 
                width="32"
                height="32"
                src={}
                alt={ingredientInfo.name}
                style={{ borderRadius: 32 }}
                unoptimized={isExternalImage}            
            /> */}
            <p style={{fontSize: 16, display: "flex", justifyContent: "flex-end"}}>{ingredientInfo.name}</p>
            <p style={{fontSize: 22, fontWeight: "bold", display: "flex", justifyContent: "flex-end"}}>{ingredientInfo.price.toLocaleString()}</p>
            <p style={{fontSize: 14, color: "inherit", display: "flex", justifyContent: "flex-end"}}>원/{ingredientInfo.quantity}{ingredientInfo.unit}</p>
        </Grid>
        <Grid item xs={10}>
            <IngredientPriceGraph priceTransitionInfo={priceTransitionInfo} inputWidth="95%" inputHeight={250} type="bar" />
        </Grid>
    </Grid>
   )
 }
