import { IngredientInfo } from "./ingredientInfo";
import { OnlineMartInfo } from "./onlineMartInfo";
import { PriceInfo } from "./priceInfo";

export interface IngredientDetailInfo {
  ingredient_info: IngredientInfo;
  views: number; // 조회수;
  price_info: PriceInfo;
  online_mart_info: OnlineMartInfo;
}
