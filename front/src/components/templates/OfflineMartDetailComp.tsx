import React, { FC, useState, useEffect } from "react";
import { CardContainer } from "../CardContainer";
import OfflineMartMap from "../OfflineMartMap";
import { Box } from "@mui/material";
import { IngredientInfo } from "../../apis/responses/ingredientInfo";
import { ApiClient } from "../../apis/apiClient";
import { useRouter } from "next/router";
import { SearchBar } from "../SearchBar";
import { IngredientItem } from "../IngredientItem";
import useGeolocation from '../../hooks/useGeolocation';

interface IProps {
  title?: string;
  ingredientId?: number;
  onClickItem?: (id: number) => void;
  inputHeight: string;
  mapId: string;
}

export const OfflineMartDetailComp: FC<IProps> = ({
  title = "주변 마트",
  ingredientId = -1,
  onClickItem,
  inputHeight,
  mapId,
}) => {
  const apiClient = ApiClient.getInstance();
  const router = useRouter();
  const location: any = useGeolocation();

  const [storeId, setStoreId] = useState(0);
  const [storeName, setStoreName] = useState("마트 이름");
  const [ingredientInfo, setIngredientInfo] = useState<IngredientInfo[]>();

  const defaultOnClickItem = (id: number) => {
    router.push(`/ingredient-info/${id}`);
  };

  const storeIdHandler = (storeid: number) => {
    setStoreId(storeid);
  };

  const storeNameHandler = (storename: string) => {
    setStoreName(storename);
  };

  useEffect(() => {
    async function getStoreInfo() {
      const storeInfo = await apiClient.getOfflineMartDetailInfo(storeId);
      setIngredientInfo(storeInfo);
    }
    getStoreInfo();
  }, [storeId]);

  return (
    <CardContainer title={title}>
      <Box>
        <Box sx={{ margin: "5% 0" }}>
          <OfflineMartMap
            ingredientId={ingredientId}
            latitude={location.coordinates.lat}
            longitude={location.coordinates.lng}
            onSetStoreId={storeIdHandler}
            onSetStoreName={storeNameHandler}
            mapId={mapId}
            inputHeight={inputHeight}
            Clickable
          />
        </Box>
        <h2>{storeName}</h2>
        <Box sx={{ margin: "5% 0" }}>
          <SearchBar color={"#F5F5F4"} />
        </Box>
        <Box
          marginBottom={"5%"}
          display="flex"
          alignItems="center"
          justifyContent="center"
          flexDirection="row"
        >
          <span style={{ fontSize: 12, fontWeight: "bold", marginLeft: "40px" }}>식재료</span>
          <Box flex={5} />
          <span style={{ fontSize: 12, fontWeight: "bold", marginRight: "40px" }}>가격</span>
          <span style={{ fontSize: 12, fontWeight: "bold", marginRight: "15px" }}>등락폭</span>
        </Box>
        {ingredientInfo?.map((data, index) => {
          return (
            <IngredientItem
              key={index}
              direction={"row"}
              ingredientInfo={data}
              onClickItem={onClickItem || defaultOnClickItem}
            />
          );
        })}
      </Box>
    </CardContainer>
  );
};
