import { IngredientInfo } from "./ingredientInfo";
import { PriceTransitionInfo } from "./priceTransitionInfo";

export interface MyRecipeDetailInfo {
  total_price: number; // 식재료 총 금액
  ingredient_list: IngredientInfo[];
  price_transition_info: PriceTransitionInfo;
  image_path: string;
  name: string;
}
