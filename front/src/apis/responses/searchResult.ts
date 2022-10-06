import { IngredientInfo } from "./ingredientInfo";
import { RecipeInfo } from "./recipeInfo";

export interface SearchResult {
  ingredient_list: IngredientInfo[];
  recipe_list: RecipeInfo[];
}
