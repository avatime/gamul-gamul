import { NextPage } from "next";
import { useEffect, useState } from "react";
import { ApiClient } from "../src/apis/apiClient";
import { IngredientOrderType } from "../src/apis/interfaces/ingredientApi";
import { getCookie } from "../src/utils/cookie";
import { IngredientInfo } from "../src/apis/responses/ingredientInfo";
import { Backdrop, Box, IconButton, Stack } from "@mui/material";
import { IngredientItem } from "../src/components/IngredientItem";
import { BackHeader } from "../src/components/BackHeader";
import { Mobile } from "../src/components/Mobile";
import { DebounceInput } from "react-debounce-input";
import SearchIcon from "@mui/icons-material/Search";
import searchStyles from "../styles/SearchHeaderBar.module.css";
import CheckIcon from "@mui/icons-material/Check";
import { useRouter } from "next/router";
import { LimitPriceNoticeInfo } from "../src/apis/responses/limitPriceNoticeInfo";
import NotificationsActiveIcon from "@mui/icons-material/NotificationsActive";
import { AlarmRegisterModal } from "../src/components/AlarmRegisterModal";
import { Desktop } from "../src/components/Desktop";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";

interface IProps {
  ingredientList: IngredientInfo[];
  limitPriceList: LimitPriceNoticeInfo[];
}

const AlarmRegisterPage: NextPage<IProps> = ({ ingredientList, limitPriceList }) => {
  const router = useRouter();
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  const [searchedIngredientList, setSearchedIngredientList] = useState<IngredientInfo[]>([]);
  const [selectedList, setSelectedList] = useState<LimitPriceNoticeInfo[]>(limitPriceList);
  useEffect(() => {
    setSearchedIngredientList(ingredientList.filter((v) => v.name.includes(searchKeyword)));
  }, [ingredientList, searchKeyword]);

  const [limitPriceNoticeInfo, setLimitPriceNoticeInfo] = useState<LimitPriceNoticeInfo>();
  const [showModal, setShowModal] = useState<boolean>(false);

  const onClickDelete = (id: number) => {
    setSelectedList((prev) => {
      const idx = prev.findIndex((v) => v.ingredient_id === id);
      return [...prev.slice(0, idx), ...prev.slice(idx + 1)];
    });
  };
  const onClickItem = (id: number) => {
    const idx = selectedList.findIndex((v) => v.ingredient_id === id);
    console.log(idx);
    if (idx < 0) {
      setLimitPriceNoticeInfo({
        ingredient_id: id,
        upper_limit_price: 0,
        lower_limit_price: 0,
      });
    } else {
      setLimitPriceNoticeInfo(selectedList[idx]);
    }
    setShowModal(true);
  };

  const onClickRegister = (limitPriceNoticeInfo: LimitPriceNoticeInfo) => {
    const idx = selectedList.findIndex(
      (v) => v.ingredient_id === limitPriceNoticeInfo?.ingredient_id
    );
    if (idx < 0) {
      setSelectedList((prev) => [...prev, limitPriceNoticeInfo!]);
    } else {
      setSelectedList((prev) => [
        ...prev.slice(0, idx),
        limitPriceNoticeInfo!,
        ...prev.slice(idx + 1),
      ]);
    }
    setShowModal(false);
  };
  const onClickSave = () => {
    ApiClient.getInstance()
      .postLimitPriceNotice(getCookie("userName"), selectedList)
      .then(() => router.back());
  };

  const Comp = (hideBackHeader: boolean) => (
    <Box bgcolor="white" minHeight="100vh">
      <Box position="fixed" width="100vw" bgcolor="white" zIndex="10">
        {!hideBackHeader && (
          <BackHeader
            backgroundColor="white"
            text="상한가/하한가 알림 등록"
            end={
              <IconButton onClick={onClickSave}>
                <CheckIcon />
              </IconButton>
            }
          />
        )}
        <Box paddingTop={hideBackHeader ? "0" : "40px"}>
          <Stack
            direction="row"
            style={{
              overflowX: "auto",
            }}
          >
            {selectedList.map((v) => (
              <IngredientItem
                key={v.ingredient_id}
                direction="column"
                ingredientInfo={ingredientList.find((it) => it.ingredient_id === v.ingredient_id)!}
                onClickItem={() => onClickItem(v.ingredient_id)}
                onDelete={() => onClickDelete(v.ingredient_id)}
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
                placeholder="식재료 검색"
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
                    selectedList.find((it) => it.ingredient_id === v.ingredient_id) ? (
                      <IconButton
                        onClick={(e: any) => {
                          onClickDelete(v.ingredient_id);
                          e.stopPropagation();
                        }}
                      >
                        <NotificationsActiveIcon
                          color="success"
                          style={{ width: "20px", height: "20px" }}
                        />
                      </IconButton>
                    ) : (
                      <IconButton>
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
                      </IconButton>
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
  );

  return (
    <>
      <Desktop>
        <Box className={styles.PageforDesktop}>{Comp(true)}</Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>{Comp(true)}</Box>
      </Tablet>
      <Mobile>{Comp(false)}</Mobile>
      {showModal && (
        <Backdrop open={showModal} style={{ zIndex: 11 }} onClick={(e: any) => setShowModal(false)}>
          <AlarmRegisterModal
            onClickRegister={onClickRegister}
            ingredientInfo={
              limitPriceNoticeInfo &&
              ingredientList.find((v) => v.ingredient_id === limitPriceNoticeInfo!.ingredient_id)
            }
            limitPriceNoticeInfo={limitPriceNoticeInfo!}
          />
        </Backdrop>
      )}
    </>
  );
};

export default AlarmRegisterPage;

export async function getStaticProps() {
  const userName = getCookie("userName");
  const apiClient = ApiClient.getInstance();
  const ingredientList = await apiClient.getIngredientList(IngredientOrderType.NAME_ASC);
  const limitPriceList = await apiClient.getLimitPriceList(userName);

  return {
    props: {
      ingredientList,
      limitPriceList,
    },
  };
}
