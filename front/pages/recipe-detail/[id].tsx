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

interface IProps {
  recipeOrderInfo: RecipeOrderInfo[];
  recipeDetailInfo: RecipeDetailInfo;
}

const RecipeDetailPage: NextPage<IProps> = ({ recipeOrderInfo }) => {
  const router = useRouter();
  const { id } = router.query;
  const userName = getCookie("userName");
  const onClickBookmark = () => {};

  return (
    <div>
  
      <Mobile>
        <BackHeader/>
        <Box
          className={styles.PageforMobile}
          sx={{ display: "flex", flexDirection: "column", height: "100vh" }}
        >
          <InfoTitle
            name={""}
            bookmark={true}
            onClickBookmark={onClickBookmark}
            views={0}
            imagePath={""}
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
                  <Image
                    src={item.image_path}
                    width="50px"
                    height="50px"
                    alt="recipe_order"
                    layout="responsive"
                    unoptimized
                    style={{borderRadius:10}}
                  />
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
            name={""}
            bookmark={false}
            onClickBookmark={function (): void {
              throw new Error("Function not implemented.");
            }}
            views={0}
            imagePath={""}
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
                      <Typography sx={{fontSize:"20px"}}>{item.description}</Typography>
                    </Box>
                    <Image
                      src={item.image_path}
                      width="300px"
                      height="300px"
                      alt="recipe_order"
                      style={{borderRadius:10}}
                      unoptimized
                      
                    />
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
            name={""}
            bookmark={false}
            onClickBookmark={function (): void {
              throw new Error("Function not implemented.");
            }}
            views={0}
            imagePath={""}
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
                    <Image
                      src={item.image_path}
                      width="300px"
                      height="300px"
                      alt="recipe_order"
                      style={{borderRadius:10}}
                      unoptimized
                    />
                  </Stack>
                </CardContainer>
              </Box>
            ))}
          </Stack>
        </Box>
      </Tablet>
    </div>
  );
};

export async function getStaticPaths() {
  const lastId = 151978;
  const paths = Array.from({ length: lastId }, (_, i) => i + 1).map((id) => ({
    params: {
      id: id.toString(),
    },
  }));
  return {
    paths,
    fallback: false,
  };
}
export default RecipeDetailPage;
export const getStaticProps = async (context: any) => {
  const apiClient = ApiClient.getInstance();

  const recipeDetailInfo = await apiClient.getRecipeDetailInfo(context.params.id);
  const recipeOrderInfo = await apiClient.getRecipeOrderList(context.params.id);
  console.log(recipeOrderInfo);

  return {
    props: {
      recipeOrderInfo,
    },
  };
};
