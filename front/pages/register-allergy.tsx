import { NextPage } from "next";
import { useEffect, useState } from "react";
import { ApiClient } from "../src/apis/apiClient";
import { IngredientOrderType } from "../src/apis/interfaces/ingredientApi";
import { getCookie } from "../src/utils/cookie";
import { IngredientInfo } from "../src/apis/responses/ingredientInfo";
import { Box, IconButton, Stack } from "@mui/material";
import { IngredientItem } from "../src/components/IngredientItem";
import { BackHeader } from "../src/components/BackHeader";
import { Mobile } from "../src/components/Mobile";
import { DebounceInput } from "react-debounce-input";
import SearchIcon from "@mui/icons-material/Search";
import searchStyles from "../styles/SearchHeaderBar.module.css";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import CheckIcon from "@mui/icons-material/Check";
import { useRouter } from "next/router";

interface IProps {
  ingredientList: IngredientInfo[];
}

const AllergyRegisterPage: NextPage<IProps> = ({ ingredientList }) => {
  const router = useRouter();
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  const [searchedIngredientList, setSearchedIngredientList] = useState<IngredientInfo[]>([]);
  const [selectedList, setSelectedList] = useState<number[]>([]);
  useEffect(() => {
    setSearchedIngredientList(ingredientList.filter((v) => v.name.includes(searchKeyword)));
  }, [ingredientList, searchKeyword]);
  const onClickDelete = (id: number) => {
    setSelectedList((prev) => {
      const idx = prev.findIndex((v) => v === id);
      return [...prev.slice(0, idx), ...prev.slice(idx + 1)];
    });
  };
  const onClickItem = (id: number) => {
    if (selectedList.find((v) => v === id)) {
      onClickDelete(id);
    } else {
      setSelectedList((prev) => [...prev, id]);
    }
  };
  const onClickSave = () => {
    ApiClient.getInstance()
      .putAllergy(getCookie("userName"), selectedList)
      .then(() => router.back());
  };

  return (
    <Mobile>
      <Box bgcolor="white" minHeight="100vh">
        <Box position="fixed" width="100vw" bgcolor="white" zIndex="10">
          <BackHeader
            backgroundColor="white"
            text="알러지 정보 등록"
            end={
              <IconButton onClick={onClickSave}>
                <CheckIcon />
              </IconButton>
            }
          />
          <Box paddingTop="50px">
            <Stack
              direction="row"
              style={{
                overflowX: "auto",
              }}
            >
              {selectedList.map((v) => (
                <IngredientItem
                  key={v}
                  direction="column"
                  ingredientInfo={ingredientList.find((it) => it.ingredient_id === v)!}
                  onClickItem={() => onClickDelete(v)}
                  onDelete={() => onClickDelete(v)}
                />
              ))}
            </Stack>

            <Box height="60px" paddingX="15px">
              <Box
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
                  style={{
                    backgroundColor: "inherit",
                  }}
                />
                <SearchIcon color="success" style={{ width: "20px", height: "20px" }} />
              </Box>
            </Box>

            <p style={{ fontSize: "16px", fontWeight: "bold", margin: "15px", color: "#A1A1AA" }}>
              식재료
            </p>
          </Box>
        </Box>

        <Stack
          direction="column"
          paddingTop={selectedList.length === 0 ? "160px" : "320px"}
          paddingBottom="60px"
        >
          {searchedIngredientList.length !== 0 ? (
            <>
              {searchedIngredientList.map((v) => (
                <Stack direction="row" key={v.ingredient_id}>
                  <IngredientItem
                    direction="row"
                    ingredientInfo={v}
                    onClickItem={() => onClickItem(v.ingredient_id)}
                    tail={
                      selectedList.find((it) => it === v.ingredient_id) ? (
                        <CheckCircleIcon
                          color="success"
                          style={{ width: "20px", height: "20px" }}
                        />
                      ) : (
                        <Box
                          style={{
                            width: "20px",
                            height: "20px",
                            borderRadius: "20px",
                            borderColor: "#D9D9D9",
                            borderWidth: "1px",
                            borderStyle: "solid",
                          }}
                        />
                      )
                    }
                  />
                </Stack>
              ))}
            </>
          ) : (
            <p style={{ fontSize: "14px", fontWeight: "bold", margin: "15px", marginTop: "30px" }}>
              검색 결과가 없어요.
            </p>
          )}
        </Stack>
      </Box>
    </Mobile>
  );
};

export default AllergyRegisterPage;

export async function getStaticProps() {
  const userName = getCookie("userName");
  const apiClient = ApiClient.getInstance();
  const ingredientList = await apiClient.getIngredientList(IngredientOrderType.NAME_ASC);
  const allergyIngredientList = await apiClient.getAllergyIngredientList(userName);

  return {
    props: {
      ingredientList,
      allergyIngredientList,
    },
  };
}
