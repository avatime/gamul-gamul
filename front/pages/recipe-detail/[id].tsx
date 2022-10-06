import { Box, Stack, Typography } from "@mui/material";
import { NextPage } from "next";
import { useRouter } from "next/router";
import { ApiClient } from "../../src/apis/apiClient";
import { RecipeOrderInfo } from "../../src/apis/responses/recipeOrderInfo";
import { BackHeader } from "../../src/components/BackHeader";
import { CardContainer } from "../../src/components/CardContainer";
import { InfoTitle } from "../../src/components/InfoTitle";
import { Mobile } from "../../src/components/Mobile";
import { MyInfoItem } from "../../src/components/MyInfoItem";
import { Navbar } from "../../src/components/Navbar";
import { RecipeDetailComp } from "../../src/components/RecipeDetailComp";
import { getCookie } from "../../src/utils/cookie";
import styles from "../../styles/Page.module.css";
import Image from "next/image";
import { saveRecentSearchLocalStorage } from "../../src/utils/localStorageUtil";
import { Key, useEffect, useState } from "react";
import { Desktop } from "../../src/components/Desktop";
import { useScroll, useSpring } from "framer-motion";
import { Tablet } from "../../src/components/Tablet";
import { RecipeDetailInfo } from "../../src/apis/responses/recipeDetailInfo";
import { Page } from "../../src/components/Page";

interface IProps {
  recipeOrderInfo: RecipeOrderInfo[];
  initialRecipeDetailInfo: RecipeDetailInfo;
}

const RecipeDetailPage: NextPage<IProps> = ({ recipeOrderInfo, initialRecipeDetailInfo }) => {
  const router = useRouter();
  const onClickBookmark = () => {};
  const { id } = router.query;

  const [userName, setUserName] = useState("");
  const [recipeDetailInfo, setRecipeDetailInfo] =
    useState<RecipeDetailInfo>(initialRecipeDetailInfo);

  useEffect(() => {
    ApiClient.getInstance().postRecipeView(Number(id));
  }, [id]);

  useEffect(() => {
    setUserName(getCookie("userName"));

    ApiClient.getInstance()
      .getRecipeDetailInfo(getCookie("userName"), Number(id))
      .then((data) => setRecipeDetailInfo(data));
    console.log(recipeDetailInfo.recipe_info.image_path);
  }, [id, recipeDetailInfo.recipe_info.image_path, userName]);

  return (
    <Page>
      <Mobile>
        <BackHeader />
        <Box
          className={styles.PageforMobile}
          sx={{ display: "flex", flexDirection: "column", height: "100vh" }}
        >
          <InfoTitle
            name={recipeDetailInfo.recipe_info.name}
            bookmark={recipeDetailInfo.recipe_info.bookmark}
            onClickBookmark={onClickBookmark}
            views={recipeDetailInfo.recipe_info.views}
            imagePath={recipeDetailInfo.recipe_info?.image_path}
            isExternalImage={true}
          />
          <Box p={2} />

          <Stack
            direction="row"
            sx={{
              overflowX: "auto",
              flex: 1,
              display: "flex",

              position: "relative",
            }}
          >
            {recipeOrderInfo.map((item, idx) => (
              <Box key={idx} sx={{ display: "flex" }}>
                <CardContainer title={""} style={{ width: "90vw", position: "relative" }}>
                  {item.image_path !== "" ? (
                    <Image
                      src={item.image_path}
                      width="50px"
                      height="50px"
                      alt="recipe_order"
                      layout="responsive"
                      unoptimized
                      style={{ borderRadius: 10 }}
                    />
                  ) : (
                    ""
                  )}
                  <Box sx={{ bottom: "10%", left: 10 }}>
                    <Typography
                      sx={{
                        color: "#4411AA",
                        fontWeight: "bold",
                        marginBottom: "10px",
                        fontSize: "20px",
                      }}
                    >
                      {idx + 1}.
                    </Typography>
                    <Typography>{item.description}</Typography>
                  </Box>
                </CardContainer>
              </Box>
            ))}
          </Stack>
        </Box>
      </Mobile>
      <Desktop>
        <Box className={styles.PageforDesktop} sx={{ flexDirection: "column", height: "100vh" }}>
          <InfoTitle
            name={recipeDetailInfo.recipe_info.name}
            bookmark={recipeDetailInfo.recipe_info.bookmark}
            onClickBookmark={onClickBookmark}
            views={recipeDetailInfo.recipe_info.views}
            imagePath={recipeDetailInfo.recipe_info.image_path}
            isExternalImage={true}
          />
          <Box p={2} />

          <Stack
            direction="column"
            sx={{
              overflowY: "auto",
            }}
          >
            {recipeOrderInfo.map((item, idx) => (
              <Box key={idx} sx={{ display: "flex" }}>
                <CardContainer title={""} style={{ width: "100vw" }}>
                  <Stack direction="row" sx={{ position: "relative" }}>
                    <Box
                      sx={{
                        margin: "auto",
                        marginLeft: "30px",
                        marginTop: "10px",
                        marginRight: "30px",
                        width: "80%",
                      }}
                    >
                      <Typography
                        sx={{
                          color: "#4411AA",
                          fontWeight: "bold",
                          fontSize: "30px",
                          marginBottom: "10px",
                        }}
                      >
                        {idx + 1}.
                      </Typography>
                      <Typography sx={{ fontSize: "20px" }}>{item.description}</Typography>
                    </Box>
                    {item.image_path !== "" ? (
                      <Image
                        src={item.image_path}
                        width="300px"
                        height="300px"
                        alt="recipe_order"
                        style={{ borderRadius: 10 }}
                        unoptimized
                      />
                    ) : (
                      ""
                    )}
                  </Stack>
                </CardContainer>
              </Box>
            ))}
          </Stack>
        </Box>
      </Desktop>
      <Tablet>
        <Box
          className={styles.PageforTablet}
          sx={{ display: "flex", flexDirection: "column", height: "100vh" }}
        >
          <InfoTitle
            name={recipeDetailInfo.recipe_info.name}
            bookmark={recipeDetailInfo.recipe_info.bookmark}
            onClickBookmark={onClickBookmark}
            views={recipeDetailInfo.recipe_info.views}
            imagePath={recipeDetailInfo.recipe_info.image_path}
            isExternalImage={true}
          />
          <Box p={2} />
          <Stack
            direction="column"
            sx={{
              overflowY: "auto",
            }}
          >
            {recipeOrderInfo.map((item, idx) => (
              <Box key={idx} sx={{ display: "flex" }}>
                <CardContainer title={""} style={{ width: "100vw" }}>
                  <Stack direction="row" sx={{ position: "relative" }}>
                    <Box
                      sx={{
                        margin: "auto",
                        marginLeft: "30px",
                        marginTop: "10px",
                        marginRight: "30px",
                        width: "80%",
                      }}
                    >
                      <Typography
                        sx={{
                          color: "#4411AA",
                          fontWeight: "bold",
                          fontSize: "30px",
                          marginBottom: "10px",
                        }}
                      >
                        {idx + 1}.
                      </Typography>
                      <Typography>{item.description}</Typography>
                    </Box>
                    {item.image_path !== "" ? (
                      <Image
                        src={item.image_path}
                        width="300px"
                        height="300px"
                        alt="recipe_order"
                        style={{ borderRadius: 10 }}
                        unoptimized
                      />
                    ) : (
                      ""
                    )}
                  </Stack>
                </CardContainer>
              </Box>
            ))}
          </Stack>
        </Box>
      </Tablet>
    </Page>
  );
};

export default RecipeDetailPage;

export const getServerSideProps = async (context: any) => {
  const apiClient = ApiClient.getInstance();

  const initialRecipeDetailInfo = await apiClient.getRecipeDetailInfo("", context.params.id);
  const recipeOrderInfo = await apiClient.getRecipeOrderList(context.params.id);

  return {
    props: {
      initialRecipeDetailInfo,
      recipeOrderInfo,
    },
  };
};
