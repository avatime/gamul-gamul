import React, { FC } from 'react'
import { IngredientInfo } from '../../apis/responses/ingredientInfo';
import { OfflineMartInfo } from '../../apis/responses/offlineMartInfo'

interface IProps {
    storeInfo: OfflineMartInfo[];
    ingredientInfo: IngredientInfo;
}

export const OfflineMartComp:FC<IProps> = (props) => {
  return (
    <div>OfflineMartComp</div>
   )
 }
