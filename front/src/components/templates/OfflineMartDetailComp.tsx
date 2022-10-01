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
import { DebounceInput } from "react-debounce-input";
import SearchIcon from "@mui/icons-material/Search";
import searchStyles from "../../../styles/SearchHeaderBar.module.css";

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
  const [ingredientInfo, setIngredientInfo] = useState<IngredientInfo[]>([]);

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

  
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  const [searchedIngredientList, setSearchedIngredientList] = useState<IngredientInfo[]>([]);
  useEffect(() => {
    setSearchedIngredientList(ingredientInfo.filter((v) => v.name.includes(searchKeyword)));
  }, [ingredientInfo, searchKeyword]);

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
        <Box height="60px" paddingX="15px">
            <Box
              maxWidth="500px"
              height="40px"
              borderRadius="20px"
              display="flex"
              bgcolor="#f5f5f5"
              alignItems="center"
              paddingX="10px"
              marginTop="10px"
            >
              <DebounceInput
                className={searchStyles.input}
                forceNotifyByEnter={true}
                forceNotifyOnBlur={true}
                value={searchKeyword}
                onChange={(e: any) => setSearchKeyword(e.target.value)}
                debounceTimeout={300}
                placeholder="식재료 검색"
                style={{
                  backgroundColor: "inherit",
                }}
              />
              <SearchIcon color="success" style={{ width: "20px", height: "20px" }} />
            </Box>
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
        {searchedIngredientList?.map((data, index) => {
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
