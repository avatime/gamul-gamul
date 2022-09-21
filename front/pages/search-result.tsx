import { Box } from "@mui/material";
import { NextPage } from "next";
import { useRouter } from "next/router";
import { ApiClient } from "../src/apis/apiClient";
import { SearchHeaderBar } from "../src/components/SearchHeaderBar";
import { IngredientListComp } from "../src/components/templates/IngredientListComp";
import { IngredientInfo } from "../src/apis/responses/ingredientInfo";
import { RecipeInfo } from "../src/apis/responses/recipeInfo";
import { RecipeListComp } from "../src/components/templates/RecipeListComp";

interface IProps {
  ingredientList: IngredientInfo[];
  recipeList: RecipeInfo[];
}

const SearchResultPage: NextPage<IProps> = ({ ingredientList, recipeList }) => {
  const router = useRouter();
  const { searchKeyword } = router.query;

  return (
    <Box className="page-background" display="flex" flexDirection="column">
      <SearchHeaderBar
        searchKeyword={searchKeyword as string}
        onClickInput={() =>
          router.push({ pathname: "/search", query: { searchKeyword } }, "/search")
        }
      />
      <Box paddingTop="60px">
        {ingredientList.length !== 0 && (
          <IngredientListComp rowSize={2} gridSize={3} ingredientList={ingredientList} />
        )}
        {recipeList.length !== 0 && (
          <RecipeListComp rowSize={2} gridSize={3} recipeList={recipeList} />
        )}
        {recipeList.length === 0 && ingredientList.length === 0 && (
          <Box
            style={{
              position: "absolute",
              left: 0,
              right: 0,
              top: 0,
              bottom: 0,
              margin: "auto",
              display: "table"
            }}
          >
            <p style={{ textAlign: "center", verticalAlign: "middle" }}>검색 결과가 없습니다.</p>
          </Box>
        )}
      </Box>
    </Box>
  );
};

export default SearchResultPage;

export async function getServerSideProps(context: any) {
  const { searchKeyword } = context.query;
  const apiClient = ApiClient.getInstance();
  const { ingredient_list, recipe_list } = await apiClient.search(searchKeyword);

  return {
    props: {
      ingredientList: ingredient_list,
      recipeList: recipe_list,
    },
  };
}
