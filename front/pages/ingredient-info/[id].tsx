import { NextPage } from "next";
import { useRouter } from "next/router";
import { useEffect } from "react";
import { saveRecentSearchLocalStorage, RecentSearch } from "../../src/utils/localStorageUtil";
import { ApiClient } from '../../src/apis/apiClient';
import { Box } from "@mui/system";
import { Desktop } from '../../src/components/Desktop';
import { Navbar } from "../../src/components/Navbar";
import styles from "../../styles/Page.module.css";
import { IngredientPriceComp } from "../../src/components/templates/IngredientPriceComp";
import { OfflineMartComp } from '../../src/components/templates/OfflineMartComp';
import { IngredientDetailInfo } from '../../src/apis/responses/ingredientDetailInfo';
import { IngredientInfo } from '../../src/apis/responses/ingredientInfo';
import { Tablet } from '../../src/components/Tablet';
import { Mobile } from '../../src/components/Mobile';

interface IProps {
  ingredientDetailInfo: IngredientDetailInfo;
  ingredientInfo: IngredientInfo;
}

const IngredientInfoPage: NextPage<IProps> = ({ ingredientDetailInfo, ingredientInfo}) => {
  const router = useRouter();
  const { id } = router.query;
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
          <IngredientPriceComp ingredientDetailInfo={ingredientDetailInfo} inputWidth={"100%"} inputHeight={500} />
          <OfflineMartComp ingredientInfo={ingredientInfo} />
          {/* OnlineMartInfoComp */}
        </Box>
        <Navbar activeIndex={1} />
      </Desktop>
      <Tablet>      
      </Tablet>
      <Mobile>

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

  return {
    props: {
      ingredientDetailInfo,
      ingredientInfo,
    },
  };
};
