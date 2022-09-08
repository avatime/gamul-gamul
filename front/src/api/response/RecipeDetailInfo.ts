import { IngredientInfo } from "./IngredientInfo";
import { RecipeInfo } from "./RecipeInfo";
import { YoutubeInfo } from "./YoutubeInfo";

export interface RecipeDetailInfo {
  recipe_info: RecipeInfo;
  ingredient_list: IngredientInfo[];
  extra_ingredient_list: string[];
  youtube_list: YoutubeInfo[];
}
