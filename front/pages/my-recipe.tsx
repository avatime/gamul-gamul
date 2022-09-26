import { Box } from "@mui/material";
import { NextPage } from "next";
import { Desktop } from "../src/components/Desktop";
import { Mobile } from "../src/components/Mobile";
import { Tablet } from "../src/components/Tablet";
import { MyRecipeComp } from "../src/components/templates/MyRecipeComp";
import styles from "../styles/Page.module.css";
import { MyRecipeInfo } from '../src/apis/responses/myRecipeInfo';
import { ApiClient } from '../src/apis/apiClient';
import { getCookie } from '../src/utils/cookie';

interface IProps {
  myRecipeList : MyRecipeInfo[];
}

const MyRecipePage: NextPage<IProps> = ({ myRecipeList }) => {
  return (
    <Box className="page-background">
      <Desktop>
        <Box className={styles.PageforDesktop}></Box>
      </Desktop>
      <Tablet>
        <Box className={styles.PageforTablet}></Box>
      </Tablet>
      <Mobile>
        <Box className={styles.PageforMobile}>
          <MyRecipeComp myRecipeList={myRecipeList} />
        </Box>
      </Mobile>
    </Box>
  );
};

export default MyRecipePage;

export const getStaticProps = async () => {
  const apiClient = ApiClient.getInstance();
  const userName = getCookie("userName");
  const List = apiClient.getMyRecipeList(userName);

  return {
    props: {
      myRecipeList: JSON.parse(JSON.stringify(List)),
    },
  };
};