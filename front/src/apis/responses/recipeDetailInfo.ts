import { IngredientInfo } from "./ingredientInfo";
import { RecipeInfo } from "./recipeInfo";
import { YoutubeInfo } from "./youtubeInfo";

export interface RecipeDetailInfo {
  recipe_info: RecipeInfo;
  ingredient_list: IngredientInfo[];
  extra_ingredient_list: string[];
  youtube_list: YoutubeInfo[];
}
