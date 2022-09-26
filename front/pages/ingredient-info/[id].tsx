import { NextPage } from "next";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { saveRecentSearchLocalStorage, RecentSearch } from "../../src/utils/localStorageUtil";
import { ApiClient } from "../../src/apis/apiClient";
import { Box } from "@mui/system";
import { Desktop } from "../../src/components/Desktop";
import { Navbar } from "../../src/components/Navbar";
import styles from "../../styles/Page.module.css";
import { IngredientPriceComp } from "../../src/components/templates/IngredientPriceComp";
import { OfflineMartComp } from "../../src/components/templates/OfflineMartComp";
import { IngredientDetailInfo } from "../../src/apis/responses/ingredientDetailInfo";
import { IngredientInfo } from "../../src/apis/responses/ingredientInfo";
import { Tablet } from "../../src/components/Tablet";
import { Mobile } from "../../src/components/Mobile";
import { InfoTitle } from "../../src/components/InfoTitle";

interface IProps {
  ingredientDetailInfo: IngredientDetailInfo;
  ingredientInfo: IngredientInfo;
  imagePath: string;
  views: number;
}

const IngredientInfoPage: NextPage<IProps> = ({ ingredientDetailInfo, ingredientInfo, imagePath, views }) => {
  const router = useRouter();
  const { id } = router.query;

  const setBookmark = () => {
    // 북마크 등록/해제 api 호출
  };

  useEffect(() => {
    if (id) {
      saveRecentSearchLocalStorage("ingredient", +(id as string), `이름 ${id}`);
    }
  }, [id]);
  return (
    <Box className="page-background">
      <Desktop>
        <Box className={styles.PageforDesktop}>
          {/* InfoTitle */}
          <IngredientPriceComp
            ingredientDetailInfo={ingredientDetailInfo}
            inputWidth={"100%"}
            inputHeight={500}
          />
          <OfflineMartComp ingredientInfo={ingredientInfo} mapId="desktop" inputHeight="400px" />
          {/* OnlineMartInfoComp */}
        </Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}>
          {/* InfoTitle */}
          <IngredientPriceComp
            ingredientDetailInfo={ingredientDetailInfo}
            inputWidth={"100%"}
            inputHeight={500}
          />
          <OfflineMartComp ingredientInfo={ingredientInfo} mapId="tablet" inputHeight="350px" />
          {/* <OfflineMartComp } />  */}
        </Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
          <InfoTitle
            name={ingredientInfo.name}
            bookmark={ingredientInfo.bookmark}
            onClickBookmark={setBookmark}
            views={views}
            imagePath={imagePath}
          />
          <IngredientPriceComp
            ingredientDetailInfo={ingredientDetailInfo}
            inputWidth={"100%"}
            inputHeight={500}
          />
          <OfflineMartComp ingredientInfo={ingredientInfo} mapId="mobile" inputHeight="300px" />
          {/* OnlineMartInfoComp */}
        </Box>
      </Mobile>
    </Box>
  );
};

export default IngredientInfoPage;

// export const getStaticPaths = async () => {

//   return {
//     paths: [],
//     fallback: true
//   }
// };

export const getServerSideProps = async (context: any) => {
  const apiClient = ApiClient.getInstance();
  const ingredientDetailInfo = await apiClient.getIngredientDetailInfo(context.params.id);
  const ingredientInfo = ingredientDetailInfo.ingredient_info;
  const imagePath = ""; // ingredientid 가지고 imagepath 설정
  const views = 0; // ingredientid 가지고 views 알아내는 api 호출

  return {
    props: {
      ingredientDetailInfo,
      ingredientInfo,
      imagePath,
      views,
    },
  };
};
