import { IngredientInfo } from "./IngredientInfo";
import { RecipeInfo } from "./RecipeInfo";

export interface SearchResult {
  ingredient_list: IngredientInfo[];
  recipe_list: RecipeInfo[];
}
