import { NextPage } from "next";
import { useRouter } from "next/router";
import { ApiClient } from "../../src/apis/apiClient";
import { getCookie } from "../../src/utils/cookie";
import { IngredientInfo } from "../../src/apis/responses/ingredientInfo";
import { PriceTransitionInfo } from "../../src/apis/responses/priceTransitionInfo";
import { Desktop } from "../../src/components/Desktop";
import { Tablet } from "../../src/components/Tablet";
import { Mobile } from "../../src/components/Mobile";
import { Avatar, Box, IconButton } from "@mui/material";
import styles from "../../styles/Page.module.css";
import EditIcon from "@mui/icons-material/Edit";
import { IngredientListComp } from "../../src/components/templates/IngredientListComp";
import IngredientPriceGraph from '../../src/components/IngredientPriceGraph';
import { ButtonFill } from '../../src/components/button/ButtonFill';
import { BackHeader } from '../../src/components/BackHeader';

interface IProps {
  totalPrice: number;
  ingredientList: IngredientInfo[];
  imagePath: string;
  name: string;
  priceTransitionInfo: PriceTransitionInfo;
}

const MyRecipeInfoPage: NextPage<IProps> = ({
  totalPrice,
  ingredientList,
  imagePath,
  name,
  priceTransitionInfo,
}) => {
  const router = useRouter();
  const { id } = router.query;

  const modifyRecipe = () => {
    router.push({
      pathname: "/register-my-recipe",
      query: { id: id },
    });
  };

  const deleteRecipe = async () => {
    // 나만의 요리법 삭제 api 호출
  }

  return (
    <Box>
      <Desktop>
        <Box className={styles.PageforDesktop}>
        <Box
          sx={{ display: "flex", margin: "10px 10px 0px 20px" }}
        >
          <Avatar
            src="/test_hamburger.jpg"
            alt="햄버거"
            sx={{ width: "60px", height: "60px" }}
          />
          <Box
            sx={{
              display: "flex",
              justifyContent: "center",
              flexDirection: "column",
              marginLeft: "20px",
              marginRight: "20px",
            }}
          >
            <h3>{name}</h3>
          </Box>
              <IconButton onClick={modifyRecipe}>
                <EditIcon color="secondary" />
              </IconButton>
        </Box>
        <IngredientListComp ingredientList={ingredientList} totalPrice={totalPrice} rowSize={2} gridSize={5} />
        <IngredientPriceGraph priceTransitionInfo={priceTransitionInfo} inputWidth="95%" inputHeight={500} type="line" />
        <Box sx={{display: "flex", justifyContent: "center", padding: "15px"}}>
          <ButtonFill onClick={deleteRecipe} text="나만의 요리법 삭제" height="50px" width="350px" maxWidth="350px" fontSize="13px" disabled={false}  />
        </Box>
        </Box>
      </Desktop>
      <Tablet className={styles.PageforTablet}>
      <Box
          sx={{ display: "flex", margin: "10px 10px 0px 20px" }}
        >
          <Avatar
            src="/test_hamburger.jpg"
            alt="햄버거"
            sx={{ width: "60px", height: "60px" }}
          />
          <Box
            sx={{
              display: "flex",
              justifyContent: "center",
              flexDirection: "column",
              marginLeft: "20px",
              marginRight: "20px",
            }}
          >
            <h3>{name}</h3>
          </Box>
              <IconButton onClick={modifyRecipe}>
                <EditIcon color="secondary" />
              </IconButton>
        </Box>
        <IngredientListComp ingredientList={ingredientList} totalPrice={totalPrice} rowSize={2} gridSize={5} />
        <IngredientPriceGraph priceTransitionInfo={priceTransitionInfo} inputWidth="95%" inputHeight={500} type="line" />
        <Box sx={{display: "flex", justifyContent: "center", padding: "15px"}}>
          <ButtonFill onClick={deleteRecipe} text="나만의 요리법 삭제" height="50px" width="350px" maxWidth="350px" fontSize="13px" disabled={false}  />
        </Box>
      </Tablet>
      <Mobile>
        <BackHeader />
        <Box
          sx={{ display: "flex", margin: "60px 10px 0px 10px" }}
        >
          <Avatar
            src="/test_hamburger.jpg"
            alt="햄버거"
            sx={{ width: "60px", height: "60px" }}
          />
          <Box
            sx={{
              display: "flex",
              justifyContent: "center",
              flexDirection: "column",
              marginLeft: "20px",
            }}
          >
            <h3>{name}</h3>
          </Box>
          <Box
            sx={{
              marginLeft: "auto",
              display: "flex",
              justifyContent: "flex-end",
            }}
          >
            <Box
              sx={{
                display: "flex",
                justifyContent: "center",
                flexDirection: "column",
              }}
            >
              <IconButton onClick={modifyRecipe}>
                <EditIcon color="secondary" />
              </IconButton>
            </Box>
          </Box>
        </Box>
        <IngredientListComp ingredientList={ingredientList} totalPrice={totalPrice} />
        <IngredientPriceGraph priceTransitionInfo={priceTransitionInfo} inputWidth="95%" inputHeight={400} type="line" />
        <Box sx={{display: "flex", justifyContent: "center", marginBottom: "75px"}}>
          <ButtonFill onClick={deleteRecipe} text="나만의 요리법 삭제" height="50px" width="350px" maxWidth="350px" fontSize="13px" disabled={false}  />
        </Box>
      </Mobile>
    </Box>
  );
};

export default MyRecipeInfoPage;

export const getServerSideProps = async (context: any) => {
  const apiClient = ApiClient.getInstance();
  const userName: string = await getCookie("userName");
  const myRecipeDetailInfo = await apiClient.getMyRecipeDetailInfo(
    userName,
    context.params.id,
  );
  const totalPrice = myRecipeDetailInfo.total_price;
  const ingredientList = myRecipeDetailInfo.ingredient_list;
  const imagePath = myRecipeDetailInfo.image_path;
  const name = myRecipeDetailInfo.name;
  const priceTransitionInfo = myRecipeDetailInfo.price_transition_info;

  return {
    props: {
      totalPrice,
      ingredientList,
      imagePath,
      name,
      priceTransitionInfo,
    },
  };
};