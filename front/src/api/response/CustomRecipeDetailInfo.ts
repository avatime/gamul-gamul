import { IngredientInfo } from "./IngredientInfo";
import { PriceInfo } from "./PriceInfo";

export interface CustomRecipeDetailInfo {
  total_price: number; // 식재료 총 금액
  ingredient_list: IngredientInfo[];
  price_info: PriceInfo;
}
