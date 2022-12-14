import { NextPage } from "next";
import { useEffect, useState } from "react";
import { ApiClient } from "../src/apis/apiClient";
import { IngredientOrderType } from "../src/apis/interfaces/ingredientApi";
import { getCookie } from "../src/utils/cookie";
import { IngredientInfo } from "../src/apis/responses/ingredientInfo";
import { Avatar, Backdrop, Box, IconButton, Input, Stack } from "@mui/material";
import { IngredientItem } from "../src/components/IngredientItem";
import { BackHeader } from "../src/components/BackHeader";
import { Mobile } from "../src/components/Mobile";
import { DebounceInput } from "react-debounce-input";
import SearchIcon from "@mui/icons-material/Search";
import searchStyles from "../styles/SearchHeaderBar.module.css";
import CheckIcon from "@mui/icons-material/Check";
import { useRouter } from "next/router";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import { Desktop } from "../src/components/Desktop";
import { Tablet } from "../src/components/Tablet";
import styles from "../styles/Page.module.css";
import { MyRecipeIngredientInfo } from "../src/apis/responses/myRecipeIngredientInfo";
import { MyRecipeIngredientModal } from "../src/components/MyRecipeIngredientModal";
import { convertFileToBase64 } from "../src/utils/fileUtil";
import AddPhotoAlternateIcon from "@mui/icons-material/AddPhotoAlternate";
import { Page } from '../src/components/Page';

interface IProps {
  ingredientList: IngredientInfo[];
}

const MyRecipeRegisterPage: NextPage<IProps> = ({ ingredientList }) => {
  const router = useRouter();

  useEffect(() => {
    if (!router.query.id) {
      return;
    }

    ApiClient.getInstance()
      .getMyRecipeIngredientList(getCookie("userName"), Number(router.query.id))
      .then((data) => setSelectedList(data));
      
    ApiClient.getInstance()
      .getMyRecipeDetailInfo(getCookie("userName"), Number(router.query.id))
      .then((data) => {
        setMyRecipeName(data.name);
        setImageDataUrl(data.image_path);
        setOriginImageDataUrl(data.image_path);
      });
  }, [router.query.id]);

  const [originImageDataUrl, setOriginImageDataUrl] = useState<string>("");
  const [imageDataUrl, setImageDataUrl] = useState<string>("");
  const [myRecipeName, setMyRecipeName] = useState<string>("");
  const [selectedList, setSelectedList] = useState<MyRecipeIngredientInfo[]>([]);

  const [searchKeyword, setSearchKeyword] = useState<string>("");
  const [searchedIngredientList, setSearchedIngredientList] = useState<IngredientInfo[]>([]);
  useEffect(() => {
    setSearchedIngredientList(ingredientList.filter((v) => v.name.includes(searchKeyword)));
  }, [ingredientList, searchKeyword]);

  const [myRecipeIngredientInfo, setMyRecipeIngredientInfo] = useState<MyRecipeIngredientInfo>();
  const [showModal, setShowModal] = useState<boolean>(false);

  const onClickDelete = (id: number) => {
    setSelectedList((prev) => {
      const idx = prev.findIndex((v) => v.ingredient_id === id);
      return [...prev.slice(0, idx), ...prev.slice(idx + 1)];
    });
  };
  const onClickItem = (id: number) => {
    const idx = selectedList.findIndex((v) => v.ingredient_id === id);
    if (idx < 0) {
      setMyRecipeIngredientInfo({
        ingredient_id: id,
        quantity: 0,
      });
    } else {
      setMyRecipeIngredientInfo(selectedList[idx]);
    }
    setShowModal(true);
  };

  const onClickRegister = (myRecipeIngredientInfo: MyRecipeIngredientInfo) => {
    if (myRecipeIngredientInfo.quantity <= 0) {
      alert("0?????? ??? ????????? ??????????????????");
      return;
    }

    const idx = selectedList.findIndex(
      (v) => v.ingredient_id === myRecipeIngredientInfo?.ingredient_id
    );
    if (idx < 0) {
      setSelectedList((prev) => [...prev, myRecipeIngredientInfo!]);
    } else {
      setSelectedList((prev) => [
        ...prev.slice(0, idx),
        myRecipeIngredientInfo!,
        ...prev.slice(idx + 1),
      ]);
    }
    setShowModal(false);
  };
  const onClickSave = () => {
    if (!imageDataUrl) {
      alert("????????? ??????????????????");
      return;
    } else if (myRecipeName.length < 3 || 10 < myRecipeName.length) {
      alert("????????? 3??? ?????? 10??? ????????? ??????????????????");
      return;
    } else if (selectedList.length == 0) {
      alert("???????????? ??????????????????");
      return;
    }

    if (router.query.id) {
      ApiClient.getInstance()
      .updateMyRecipe(
        getCookie("userName"),
        Number(router.query.id),
        originImageDataUrl === imageDataUrl ? "" : imageDataUrl,
        myRecipeName,
        selectedList
      )
      .then(() => router.back());
    } else {
      ApiClient.getInstance()
      .postMyRecipe(
        getCookie("userName"),
        originImageDataUrl === imageDataUrl ? "" : imageDataUrl,
        myRecipeName,
        selectedList
      )
      .then(() => router.back());
    }

    
  };

  const uploadImage = (e: any) => {
    if (e.target.files.length) {
      convertFileToBase64(e.target.files[0], (base64) => setImageDataUrl(base64));
    }
  };

  const Comp = (hideBackHeader: boolean, navBarWidth?: String) => (
    <Box bgcolor="white" minHeight="100vh">
      <Box
        position="fixed"
        width={`calc(100% - ${navBarWidth || "0px"})`}
        bgcolor="white"
        zIndex="10"
      >
        {!hideBackHeader && (
          <BackHeader
            backgroundColor="white"
            text={`????????? ????????? ${!originImageDataUrl ? "??????" : "??????"}`}
            end={
              <IconButton onClick={onClickSave}>
                <CheckIcon />
              </IconButton>
            }
          />
        )}
        {hideBackHeader && (
          <Box display="flex" flexDirection="row" justifyContent="end">
            <IconButton onClick={onClickSave}>
              <CheckIcon />
            </IconButton>
          </Box>
        )}
        <Box paddingTop={hideBackHeader ? "0" : "40px"}>
          <Box
            display="flex"
            flexDirection="row"
            alignItems="center"
            paddingRight="15px"
            maxWidth="500px"
          >
            <form method="post" encType="multipart/form-data">
              <label>
                {!imageDataUrl ? (
                  <Avatar style={{ width: 60, height: 60, cursor: "pointer", margin: "15px" }}>
                    <AddPhotoAlternateIcon />
                  </Avatar>
                ) : (
                  <Avatar
                    src={imageDataUrl}
                    style={{ width: 60, height: 60, cursor: "pointer", margin: "15px" }}
                  />
                )}

                <input
                  type="file"
                  accept="image/*"
                  style={{ display: "none" }}
                  onChange={uploadImage}
                />
              </label>
            </form>
            <Input
              color="success"
              sx={{ flex: 1 }}
              placeholder="????????? ????????? ????????? ???????????????."
              onChange={(e: any) => setMyRecipeName(e.target.value)}
              value={myRecipeName}
            />
          </Box>
          <Stack
            direction="row"
            style={{
              overflowX: "auto",
            }}
          >
            {selectedList.map((v) => {
              const ingredient = ingredientList.find((it) => it.ingredient_id === v.ingredient_id)!;
              return (
                <Box minWidth="95px" key={v.ingredient_id}>
                  <IngredientItem
                    direction="column"
                    ingredientInfo={
                      ingredientList.find((it) => it.ingredient_id === v.ingredient_id)!
                    }
                    onClickItem={() => onClickItem(v.ingredient_id)}
                    onDelete={() => onClickDelete(v.ingredient_id)}
                    title={`${ingredient.name} (${v.quantity}${ingredient.unit})`}
                  />
                </Box>
              );
            })}
          </Stack>

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
                placeholder="????????? ??????"
                style={{
                  backgroundColor: "inherit",
                }}
              />
              <SearchIcon color="success" style={{ width: "20px", height: "20px" }} />
            </Box>
          </Box>

          <p style={{ fontSize: "16px", fontWeight: "bold", margin: "15px", color: "#A1A1AA" }}>
            ?????????
          </p>
        </Box>
      </Box>

      <Stack
        direction="column"
        paddingTop={selectedList.length === 0 ? "240px" : "420px"}
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
                      <CheckCircleIcon color="success" style={{ width: "20px", height: "20px" }} />
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
            ?????? ????????? ?????????.
          </p>
        )}
      </Stack>
    </Box>
  );

  return (
    <Page>
      <Desktop>
        <Box className={styles.PageforDesktop}>{Comp(true, "250px")}</Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>{Comp(true, "150px")}</Box>
      </Tablet>
      <Mobile>{Comp(false)}</Mobile>
      {showModal && (
        <Backdrop open={showModal} style={{ zIndex: 11 }} onClick={(e: any) => setShowModal(false)}>
          <MyRecipeIngredientModal
            onClickRegister={onClickRegister}
            ingredientInfo={
              ingredientList.find((v) => v.ingredient_id === myRecipeIngredientInfo!.ingredient_id)!
            }
            myRecipeIngredientInfo={myRecipeIngredientInfo!}
          />
        </Backdrop>
      )}
    </Page>
  );
};

export default MyRecipeRegisterPage;

export async function getServerSideProps(context: any) {
  const apiClient = ApiClient.getInstance();
  const ingredientList = await apiClient.getIngredientList(IngredientOrderType.NAME_ASC);

  return {
    props: {
      ingredientList,
    },
  };
}
