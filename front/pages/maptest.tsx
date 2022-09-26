import { NextPage } from "next";
import React, { FC, Fragment, useEffect, useState } from "react";
import { OfflineMartInfo } from "../src/apis/responses/offlineMartInfo";
import OfflineMartMap from "../src/components/OfflineMartMap";
import useGeolocation from "../src/hooks/useGeolocation";
import { ApiClient } from "../src/apis/apiClient";
import { Box } from "@mui/material";
import { OfflineMartInfoItem } from "../src/components/OfflineMartInfoItem";
import { IngredientInfo } from "../src/apis/responses/ingredientInfo";

interface IProps {
  storeInfo: OfflineMartInfo[];
  ingredientInfo: IngredientInfo;
}

const Maptest: NextPage<IProps> = ({ storeInfo, ingredientInfo }) => {
  const [storeId, setStoreId] = useState(0);
  const [storeName, setStoreName] = useState("");
  const [stores, setStores] = useState<OfflineMartInfo[]>(storeInfo);

  const storeIdHandler = (storeid: number) => {
    setStoreId(storeid);
  };

  const storeNameHandler = (storename: string) => {
    setStoreName(storename);
  };

  const storesHandler = (marts: OfflineMartInfo[]) => {
    setStores(marts);
  };

  const location: any = useGeolocation();

  return (
    <Box style={{ padding: 15 }}>
      <h2>주변 마트</h2>
      <OfflineMartMap
        ingredientId={ingredientInfo.ingredient_id}
        latitude={location.coordinates.lat}
        longitude={location.coordinates.lng}
        onSetStoreId={storeIdHandler}
        onSetStoreName={storeNameHandler}
        onSetStores={storesHandler}
        mapId="test"
        inputHeight="300px"
      />
      <h3>마트 이름</h3>
      <h3>{storeName}</h3>
      <br />
      <br />
      <Box sx={{ width: "90%" }}>
        <OfflineMartInfoItem ingredientInfo={ingredientInfo} />
        <OfflineMartInfoItem offlineMartInfo={storeInfo[0]} ingredientInfo={ingredientInfo} />
      </Box>
    </Box>
  );
};

export default Maptest;

export async function getServerSideProps() {
  const apiClient = ApiClient.getInstance();
  // 실 구현시 useEffect로 변환 가능성
  const storeInfo: OfflineMartInfo[] = await apiClient.getOfflineMartList(1, 1, 1, 1, 1, 1, 1);
  const ingredientInfo: IngredientInfo = (await apiClient.getIngredientDetailInfo(1))
    .ingredient_info;
  return {
    props: {
      storeInfo,
      ingredientInfo,
    },
  };
}
