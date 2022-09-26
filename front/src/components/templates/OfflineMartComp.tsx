import React, { FC, useState } from "react";
import { IngredientInfo } from "../../apis/responses/ingredientInfo";
import { OfflineMartInfo } from "../../apis/responses/offlineMartInfo";
import { useRouter } from "next/router";
import { CardContainer } from "../CardContainer";
import OfflineMartMap from "../OfflineMartMap";
import useGeolocation from "../../hooks/useGeolocation";
import { Box } from '@mui/material';
import { OfflineMartInfoItem } from "../OfflineMartInfoItem";

interface IProps {
  title?: string;
  ingredientInfo: IngredientInfo;
  mapId: string;
  inputHeight: string;
}

export const OfflineMartComp: FC<IProps> = ({ title = "주변 마트", ingredientInfo, mapId, inputHeight }) => {
  const [stores, setStores] = useState<OfflineMartInfo[]>();

  const router = useRouter();
  const location: any = useGeolocation();

  const storesHandler = (marts: OfflineMartInfo[]) => {
    setStores(marts);
  };

  const movePage = () => {
    router.push(`/store-info/${ingredientInfo.ingredient_id}`);
  };

  return (
    <CardContainer title={title} onClickMore={movePage}>
      <Box>
        <OfflineMartMap
          mapId={mapId}
          ingredientId={ingredientInfo.ingredient_id}
          latitude={location.coordinates.lat}
          longitude={location.coordinates.lng}
          onSetStores={storesHandler} inputHeight={inputHeight} />
        <Box marginTop="10px">
        <OfflineMartInfoItem ingredientInfo={ingredientInfo}/>
        {stores?.map((data, index) => {
            return <OfflineMartInfoItem key={index} ingredientInfo={ingredientInfo} offlineMartInfo={data} />
        })}
        </Box>
        
      </Box>
    </CardContainer>
  );
};
