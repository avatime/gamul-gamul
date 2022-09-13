import { IngredientInfo } from "./ingredientInfo";
import { OnlineMartInfo } from "./onlineMartInfo";
import { PriceTransitionInfo } from "./priceTransitionInfo";

export interface IngredientDetailInfo {
  ingredient_info: IngredientInfo;
  views: number; // 조회수;
  price_transition_info: PriceTransitionInfo;
  online_mart_info: OnlineMartInfo[];
}
