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
        <IngredientListComp rowSize={2} gridSize={3} ingredientList={ingredientList} />
        <RecipeListComp rowSize={2} gridSize={3} recipeList={recipeList} />
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
