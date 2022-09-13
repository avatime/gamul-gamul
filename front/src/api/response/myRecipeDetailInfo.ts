import { IngredientInfo } from "./ingredientInfo";
import { PriceInfo } from "./priceInfo";

export interface MyRecipeDetailInfo {
  total_price: number; // 식재료 총 금액
  ingredient_list: IngredientInfo[];
  price_info: PriceInfo;
}
